# Inventory Management
A demo project for Inventory Management

### Prerequisite
    Java version : 8
    Maven version : 4.0.0 or above.

### Services
    1.  eureka-service
        This acts as a Eureka Server.
    
    2.  order-service
        This service handles all Order operations.
        
    3.  item-service
        This service handles all Item operations.
        
### Endpoints
    1. order-service
        a. Create Order
            i.  HTTP POST - http://localhost:8888/order
            ii. Request Body Format
              {
                "customerName":"abc",
                "orderDate": "2020-07-20",
                "shippingAddress": "xyz",
                "items": [{
                  "productCode": 1,
                  "quantity": 10
                }]
              }
        b.  Get Order By Id
            HTTP GET - http://localhost:8888/order/{id}

        c.  Get All Orders
            HTTP GET - http://localhost:8888/order
    
    2. item-service
        a. Create Item
            i.  HTTP POST - http://localhost:9999/item
            ii. Request Body Format
              {
                "productName" : "Apple",
                "quantity" : 80
              }
        b.  Get Item By Id
            HTTP GET - http://localhost:9999/item/{id}

        c.  Get All Items
            HTTP GET - http://localhost:9999/item
