package com.betfair.aping.entities;

import com.betfair.aping.enums.MarketBettingType;
import com.betfair.aping.enums.OrderStatus;
import lombok.Value;

import java.util.Set;

@Value
public class MarketFilter {

	private String textQuery = null;

	private Set<String> exchangeIds = null;

	private Set<String> eventTypeIds = null;

	private Set<String> marketIds = null;

	private Boolean inPlayOnly = null;

	private Set<String> eventIds = null;

	private Set<String> competitionIds = null;

	private Set<String> venues = null;

	private Boolean bspOnly = null;

	private Boolean turnInPlayEnabled = null;

	private Set<MarketBettingType> marketBettingTypes = null;

	private Set<String> marketCountries = null;

	private Set<String> marketTypeCodes = null;

	private TimeRange marketStartTime = null;

	private Set<OrderStatus> withOrders = null;

	public MarketFilter() {

    }
}
