package com.betfair.aping.entities;

import com.betfair.aping.enums.MarketBettingType;
import com.betfair.aping.enums.OrderStatus;
import lombok.Value;

import java.util.Set;

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

    public String getTextQuery() {
        return textQuery;
    }

    public void setTextQuery(String textQuery) {
        this.textQuery = textQuery;
    }

    public Set<String> getExchangeIds() {
        return exchangeIds;
    }

    public void setExchangeIds(Set<String> exchangeIds) {
        this.exchangeIds = exchangeIds;
    }

    public Set<String> getEventTypeIds() {
        return eventTypeIds;
    }

    public void setEventTypeIds(Set<String> eventTypeIds) {
        this.eventTypeIds = eventTypeIds;
    }

    public Set<String> getMarketIds() {
        return marketIds;
    }

    public void setMarketIds(Set<String> marketIds) {
        this.marketIds = marketIds;
    }

    public Boolean getInPlayOnly() {
        return inPlayOnly;
    }

    public void setInPlayOnly(Boolean inPlayOnly) {
        this.inPlayOnly = inPlayOnly;
    }

    public Set<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<String> eventIds) {
        this.eventIds = eventIds;
    }

    public Set<String> getCompetitionIds() {
        return competitionIds;
    }

    public void setCompetitionIds(Set<String> competitionIds) {
        this.competitionIds = competitionIds;
    }

    public Set<String> getVenues() {
        return venues;
    }

    public void setVenues(Set<String> venues) {
        this.venues = venues;
    }

    public Boolean getBspOnly() {
        return bspOnly;
    }

    public void setBspOnly(Boolean bspOnly) {
        this.bspOnly = bspOnly;
    }

    public Boolean getTurnInPlayEnabled() {
        return turnInPlayEnabled;
    }

    public void setTurnInPlayEnabled(Boolean turnInPlayEnabled) {
        this.turnInPlayEnabled = turnInPlayEnabled;
    }

    public Set<MarketBettingType> getMarketBettingTypes() {
        return marketBettingTypes;
    }

    public void setMarketBettingTypes(Set<MarketBettingType> marketBettingTypes) {
        this.marketBettingTypes = marketBettingTypes;
    }

    public Set<String> getMarketCountries() {
        return marketCountries;
    }

    public void setMarketCountries(Set<String> marketCountries) {
        this.marketCountries = marketCountries;
    }

    public Set<String> getMarketTypeCodes() {
        return marketTypeCodes;
    }

    public void setMarketTypeCodes(Set<String> marketTypeCodes) {
        this.marketTypeCodes = marketTypeCodes;
    }

    public TimeRange getMarketStartTime() {
        return marketStartTime;
    }

    public void setMarketStartTime(TimeRange marketStartTime) {
        this.marketStartTime = marketStartTime;
    }

    public Set<OrderStatus> getWithOrders() {
        return withOrders;
    }

    public void setWithOrders(Set<OrderStatus> withOrders) {
        this.withOrders = withOrders;
    }
}
