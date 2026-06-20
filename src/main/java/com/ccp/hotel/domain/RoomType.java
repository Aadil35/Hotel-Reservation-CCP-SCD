package com.ccp.hotel.domain;
public class RoomType {
    private final RoomKind kind; private final Money cost;
    public RoomType(RoomKind kind, Money cost){this.kind=Validation.notNull(kind,"room kind"); this.cost=Validation.notNull(cost,"cost");}
    public RoomKind getKind(){return kind;} public Money getCost(){return cost;}
    @Override public String toString(){return kind + " (" + cost + ")";}
}
