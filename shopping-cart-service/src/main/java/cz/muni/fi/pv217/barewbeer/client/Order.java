package cz.muni.fi.pv217.barewbeer.client;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    public List<OrderItem> items;
    public LocalDateTime creationTimestamp;
    public LocalDateTime updateTimestamp;
    public boolean confirmed;
    public boolean shippedOut;
}
