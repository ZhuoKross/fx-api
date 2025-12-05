CREATE OR REPLACE PROCEDURE calculate_settlement_date(
	IN i_source_currency VARCHAR(10),
	IN i_destiny_currency VARCHAR(10),
	IN i_date_transaction DATE,
	IN i_action VARCHAR(10),
	OUT o_settlement_date DATE
	)
LANGUAGE plpgsql
AS $$
DECLARE
	is_holiday_on_source_currency BOOLEAN;
	is_holiday_on_destiny_currency BOOLEAN;
	dow INT;

BEGIN

	dow := EXTRACT(DOW FROM i_date_transaction);

	SELECT EXISTS(
		SELECT 1 FROM calendar
		WHERE country_code = i_source_currency
		AND date = i_date_transaction
	)INTO is_holiday_on_source_currency;

	SELECT EXISTS(
		SELECT 1 FROM calendar
		WHERE country_code = i_destiny_currency
		AND date = i_date_transaction
	)INTO is_holiday_on_destiny_currency;

	o_settlement_date := i_date_transaction;


	IF i_action = 'SALE' THEN
		IF i_source_currency = 'USD' AND i_destiny_currency = 'COP' THEN
			IF dow BETWEEN 1 AND 5 THEN
				o_settlement_date = o_settlement_date;
			ELSEIF dow = 0 THEN
				o_settlement_date = o_settlement_date + INTERVAL '1 day';
			ELSEIF dow = 6 THEN
				o_settlement_date = o_settlement_date + INTERVAL '2 day';
			END IF;
		END IF;

		IF i_source_currency = 'EUR' AND i_destiny_currency = 'COP' THEN
			IF dow BETWEEN 1 AND 5 THEN
				o_settlement_date = o_settlement_date + INTERVAL '2 day';
			ELSEIF dow = 0 THEN
				o_settlement_date = o_settlement_date + INTERVAL '3 day';
			ELSEIF dow = 6 THEN
				o_settlement_date = o_settlement_date + INTERVAL '4 day';
			END IF;
		END IF;

		IF i_source_currency = 'CAD' AND i_destiny_currency = 'COP' THEN
			IF dow BETWEEN 1 AND 5 THEN
				o_settlement_date = o_settlement_date + INTERVAL '1 day';
			ELSEIF dow = 0 THEN
				o_settlement_date = o_settlement_date + INTERVAL '2 day';
			ELSEIF dow = 6 THEN
			 	o_settlement_date = o_settlement_date + INTERVAL '3 day';
			END IF;
		END IF;
	END IF;


	IF i_action = 'PURCHASE' THEN
		IF dow BETWEEN 1 AND 5 THEN
			o_settlement_date = o_settlement_date;
		ELSEIF dow = 0 AND (is_holiday_on_source_currency OR is_holiday_on_destiny_currency) THEN
			o_settlement_date = o_settlement_date + INTERVAL '2 day';
		ELSEIF dow = 6 THEN
			o_settlement_date = o_settlement_date + INTERVAL '2 day';
		ELSEIF dow = 0 THEN
			o_settlement_date = o_settlement_date + INTERVAL '1 day';
		ELSEIF dow BETWEEN 1 AND 5 THEN
			o_settlement_date = o_settlement_date;
		END IF;
	END IF;

END
$$;