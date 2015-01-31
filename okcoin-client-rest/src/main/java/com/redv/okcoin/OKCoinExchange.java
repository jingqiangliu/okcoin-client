package com.redv.okcoin;

import java.util.Arrays;
import java.util.List;

import si.mazi.rescu.SynchronizedValueFactory;

import com.redv.okcoin.service.polling.OKCoinAccountService;
import com.redv.okcoin.service.polling.OKCoinMarketDataService;
import com.redv.okcoin.service.polling.OKCoinTradeService;
import com.xeiam.xchange.BaseExchange;
import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;

public class OKCoinExchange extends BaseExchange {

	/**
	 * The parameter name of the symbols that will focus on.
	 */
	public static final String SYMBOLS_PARAMETER = "symbols";

	private static final List<CurrencyPair> SYMBOLS = Arrays.asList(
			CurrencyPair.BTC_CNY,
			CurrencyPair.LTC_CNY);

	@Override
	public void applySpecification(ExchangeSpecification exchangeSpecification) {
		super.applySpecification(exchangeSpecification);
		this.pollingMarketDataService = new OKCoinMarketDataService(this);
		if (exchangeSpecification.getApiKey() != null) {
			this.pollingAccountService = new OKCoinAccountService(this);
			this.pollingTradeService = new OKCoinTradeService(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {
		ExchangeSpecification exchangeSpecification = new ExchangeSpecification(
				this.getClass().getCanonicalName());
		exchangeSpecification.setSslUri("https://www.okcoin.cn/api");
		exchangeSpecification.setHost("www.okcoin.cn");
		exchangeSpecification.setExchangeName("OKCoin");
		exchangeSpecification
				.setExchangeDescription("OKCoin is a globally oriented crypto-currency trading platform.");
		exchangeSpecification.setExchangeSpecificParametersItem(
				SYMBOLS_PARAMETER, SYMBOLS);
		return exchangeSpecification;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SynchronizedValueFactory<Long> getNonceFactory() {
		return null;
	}

}
