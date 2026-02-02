Here are the notes for the three major categories of design patterns as defined in your documents.

---

## **1. Creational Design Patterns**

* **Definition:** These patterns focus on **object creation mechanisms**. They aim to create objects in a manner suitable to the situation, increasing flexibility and allowing for the reuse of existing code by decoupling the system from how its objects are created and composed.
* **Major Types:**
* **Singleton:** Ensures only one instance of a class exists (e.g., a central Payment Gateway).
* **Factory:** Creates objects without specifying the exact class of object that will be created (e.g., a Notification system for SMS/Email).
* **Builder:** Constructs complex objects step-by-step (e.g., creating a customized e-commerce Order with optional fields).


* **Real-Life Context:** Used when the system needs to be independent of how its products are created, such as initializing a database connection pool or generating various types of user notifications.

---

## **2. Structural Design Patterns**

* **Definition:** These patterns focus on **how classes and objects are composed** to form larger, more efficient structures. They help ensure that if one part of a system changes, the entire structure doesn't need to change, maintaining flexibility and efficiency.
* **Major Types:**
* **Adapter:** Allows incompatible interfaces to work together (e.g., standardizing different third-party payment APIs like PayPal and Stripe).
* **Decorator:** Dynamically adds new responsibilities to an object (e.g., adding "Gift Wrap" or "Warranty" features to a basic Product).
* **Facade:** Provides a simplified, single interface to a complex set of subsystems (e.g., a single `placeOrder` method that talks to Inventory, Shipping, and Payment).
* **Composite:** Treats individual objects and compositions of objects uniformly (e.g., treating a single item and a "Product Bundle" the same in a cart).


* **Real-Life Context:** Used to simplify relationships between entities and to integrate legacy or third-party code into a modern application.

---

## **3. Behavioral Design Patterns**

* **Definition:** These patterns focus on **object interaction and communication**. They define how objects talk to each other and how responsibilities are distributed among them to ensure a clear flow of logic and loose coupling.
* **Major Types:**
* **Strategy:** Defines a family of interchangeable algorithms (e.g., switching between "Seasonal Discount" and "Loyalty Discount" logic at runtime).
* **Observer:** A subscription mechanism to notify multiple objects about events (e.g., notifying SMS and Email services when an Order status changes to "Shipped").
* **Command:** Encapsulates a request as an object (e.g., representing "Place Order" or "Cancel Order" as commands that can be executed or undone).
* **Mediator:** Restricts direct communication between objects and forces them to collaborate via a mediator (e.g., an `OrderProcessor` coordinating Inventory and Shipping).


* **Real-Life Context:** Used to manage complex control flows and communication logic, such as updating a UI when data changes or handling complex business workflows.

---

## **Singleton Design Pattern**

*
**When to Use:** Use this pattern when you need to ensure that only one instance of a class exists throughout the application’s lifecycle. It is essential for shared resources where multiple instances would cause conflicts or resource waste.


* **Real-Life Examples:**
* **Payment Gateway Coordinator:** Ensuring only one payment processor instance handles transactions for platforms like Amazon or Flipkart to maintain a consistent state.


* **Log Manager:** A single instance that handles writing logs to a file to prevent multiple threads from corrupting the file simultaneously.
* **Database Connection Pool:** Managing a central pool of connections so the application doesn't exhaust database resources by opening too many instances.


* **Implementation:**
* Declare a `private static volatile` variable to hold the single instance.


* Create a **private constructor** to prevent external instantiation via the `new` keyword.


* Use a **double-locking mechanism** inside a `public static` method.


* Perform an initial null check, enter a `synchronized` block on the class, and perform a second null check before creating the instance.




* **Advantages:**
* Prevents multiple payment processor instances from being created unnecessarily.


* Ensures **thread safety** during multi-threaded transactions by preventing race conditions during instance creation.





### **Code Implementation**

```java
package org.example.creational.singleton;

public class PaymentGateway {
    // Volatile ensures visibility of changes across threads [cite: 10]
    private static volatile PaymentGateway paymentGatewayInstance;

    // Private constructor [cite: 11]
    private PaymentGateway() {
        System.out.println("Initializing one instance ...");
    }

    // Double locking mechanism for thread safety [cite: 14, 15]
    public static PaymentGateway getPaymentGatewayInstance() {
        if (paymentGatewayInstance == null) { // First check [cite: 16]
            synchronized (PaymentGateway.class) { // Lock [cite: 17]
                if (paymentGatewayInstance == null) { // Second check [cite: 18]
                    paymentGatewayInstance = new PaymentGateway(); // [cite: 19]
                }
            }
        }
        return paymentGatewayInstance; 
    }

    public void processPayment(String orderId, double amount) {
        System.out.println(" Processing the amount of : " + amount + " against the order id : " + orderId); 
    }
}

```

Here are the notes for the **Factory Design Pattern** based on your documents, structured in the same format with real-world examples and code.

---

## **Factory Design Pattern**

* **When to Use:** Use this pattern when you want to create objects without exposing the instantiation logic to the client. It is ideal when a class cannot anticipate the type of objects it needs to create or when you want to delegate responsibility to a specialized creator.
* **Real-Life Examples:**
* **Notification System:** A platform that needs to send alerts via different channels (Email, SMS, Push, WhatsApp) depending on user preference.
* **Logistics/Shipping:** A system that creates different transport objects (Truck, Ship, Plane) based on the delivery type.
* **UI Component Library:** Generating different buttons or icons (Windows, MacOS, Linux) depending on the operating system the app is running on.


* **Implementation:**
* Define an **Interface** or **Abstract Class** (e.g., `Notification`) that all concrete products will implement.
* Create **Concrete Classes** (e.g., `EmailNotification`, `SMSNotification`) that provide specific implementations of the interface.
* Create a **Factory Class** with a static method that takes an input (like a String or Enum) and returns an instance of the interface.
* Use conditional logic (`if-else` or `switch`) inside the factory to decide which concrete class to instantiate.


* **Advantages:**
* **Loose Coupling:** The client code is not tied to specific classes; it only interacts with the interface.
* **Scalability:** You can add new product types (e.g., `TelegramNotification`) without changing the code that uses the factory.
* **Consistency:** Centralizes object creation logic in one place.



### **Code Implementation**

```java
// 1. The Interface
package org.example.creational.factory;

public interface Notification {
    void sendNotification();
}

// 2. Concrete Implementations
class SMSNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending an SMS notification...");
    }
}

class EmailNotification implements Notification {
    @Override
    public void sendNotification() {
        System.out.println("Sending an Email notification...");
    }
}

// 3. The Factory Class
public class NotificationFactory {
    public static Notification createNotification(String channel) {
        if (channel == null || channel.isEmpty()) {
            return null;
        }
        // Logic to return the appropriate object
        if (channel.equalsIgnoreCase("SMS")) {
            return new SMSNotification();
        } else if (channel.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        }
        throw new IllegalArgumentException("Unknown channel " + channel);
    }
}

// 4. Client Code Usage
// Notification n = NotificationFactory.createNotification("SMS");
// n.sendNotification();

```

---

Here are the notes for the **Builder Design Pattern** based on your documents, focusing on the construction of complex objects.

---

## **Builder Design Pattern**

* **When to Use:** Use this pattern when an object requires a complex, step-by-step construction process, especially when there are many optional parameters or configurations. It is the best solution for avoiding "telescoping constructors" (constructors with a long list of parameters).
* **Real-Life Examples:**
* **E-commerce Order Customization:** Building an order for a laptop where the user chooses RAM, storage, processor, and optional add-ons like a laptop bag or extended warranty.
* **Pizza Ordering:** A pizza object where only the size is mandatory, but toppings (cheese, olives, pepperoni) and crust types are optional.
* **Document Generator:** Creating a PDF document where you can optionally add a header, footer, watermark, or page numbers step-by-step.


* **Implementation:**
* Create a **Static Nested Builder Class** inside the main class (e.g., `OrderBuilder` inside `Order`).
* The Builder class should have the same fields as the main class.
* Implement **Setter-like methods** for each attribute that return `this` (the builder instance), enabling "method chaining."
* Implement a `build()` method in the Builder class that calls the private constructor of the main class to return the final object.


* **Advantages:**
* **Readability:** The client code is much easier to read and maintain.
* **Immutability:** You can make the main object immutable (no setters) because all values are set during construction.
* **Flexibility:** Allows you to create different representations of an object using the same construction process.



### **Code Implementation**

```java
package org.example.creational.builder;

public class Order {
   private final String productName; // Mandatory
   private final String size;        // Optional
   private final String color;       // Optional
   private final boolean giftWrapped; // Optional

   // Private constructor only accessible by the Builder
   private Order(OrderBuilder builder) {
       this.productName = builder.productName;
       this.size = builder.size;
       this.color = builder.color;
       this.giftWrapped = builder.giftWrapped;
   }

   public static class OrderBuilder {
       private final String productName;
       private String size;
       private String color;
       private boolean giftWrapped;

       public OrderBuilder(String productName) {
           this.productName = productName;
       }

       public OrderBuilder size(String size) {
           this.size = size;
           return this;
       }

       public OrderBuilder color(String color) {
           this.color = color;
           return this;
       }

       public OrderBuilder giftWrapped(boolean giftWrapped) {
           this.giftWrapped = giftWrapped;
           return this;
       }

       public Order build() {
           return new Order(this);
       }
   }
}

// Client Usage
// Order laptop = new Order.OrderBuilder("Laptop")
//                .size("15 inch")
//                .color("Space Grey")
//                .giftWrapped(true)
//                .build();

```

---

Here are the notes for the first major Structural Design Pattern: the **Adapter Pattern**.

---

## **1. Adapter Design Pattern**

* **When to Use:** Use this pattern when you need to integrate a class or a third-party service into your system, but its interface is incompatible with your existing code. It acts as a "wrapper" or "bridge" between two different interfaces.
* **Real-Life Examples:**
* **Payment Gateway Integration:** Your application expects a standard `pay()` method, but different providers like **PayPal**, **Stripe**, or **PhonePe** have their own specific methods (e.g., `makePayment()`, `processTransaction()`). An adapter translates your call to theirs.
* **Memory Card Reader:** Your laptop has a USB port (interface), but your memory card has a different connection. The card reader acts as the adapter so the laptop can read the card.
* **Multi-Region Power Plug:** Using a travel adapter to connect a European plug into an Indian power socket.


* **Implementation:**
* Define a **Target Interface** (e.g., `PaymentPreprocessor`) that your application uses.
* Identify the **Adaptee** (the third-party class with the incompatible interface, e.g., `PayPal`).
* Create an **Adapter Class** that implements the Target Interface and holds a reference to the Adaptee.
* Inside the implemented method, map the call to the Adaptee’s specific method.


* **Advantages:**
* Allows incompatible classes to work together without changing their original source code.
* Promotes the **Open/Closed Principle**; you can add adapters for new third-party services without breaking your core logic.



### **Code Implementation**

```java
// 1. Target Interface (What our app expects)
package org.example.structural.adapter;

public interface PaymentPreprocessor {
    void pay(double amount);
}

// 2. Adaptee (Third-party class with different methods)
class PhonePe {
    public void phonePePayment(double amount) {
        System.out.println("Paid " + amount + " using PhonePe.");
    }
}

// 3. The Adapter
public class PhonePeAdapter implements PaymentPreprocessor {
    private PhonePe phonePe = new PhonePe();

    @Override
    public void pay(double amount) {
        // Translating 'pay' to 'phonePePayment'
        phonePe.phonePePayment(amount);
    }
}

// 4. Client Usage
// PaymentPreprocessor processor = new PhonePeAdapter();
// processor.pay(5000);

```

---
Moving forward with the **Structural Design Patterns**, let's dive into the **Decorator Pattern**.

---

## **2. Decorator Design Pattern**

* **When to Use:** Use this pattern when you want to add new functionality or behaviors to an object dynamically without altering its original structure or code. It is a flexible alternative to subclassing for extending functionality.
* **Real-Life Examples:**
* **Product Add-ons:** Starting with a base product (like a T-shirt) and dynamically adding "Gift Wrapping" or "Extended Warranty" during the checkout process.
* **Coffee Shop App:** Ordering a basic coffee and adding decorators for "Milk," "Sugar," or "Whipped Cream," each adding its own cost and description.
* **Data Compression/Encryption:** Wrapping a simple file stream with a "Compressor Decorator" and then an "Encryption Decorator."


* **Implementation:**
* Create a **Component Interface** (e.g., `Product`) that defines the basic operations.
* Create a **Base Decorator class** that implements this interface and holds a reference to a `Product` object.
* Create **Concrete Decorator classes** (e.g., `WarrantyDecorator`) that extend the base decorator and override methods to add their specific behavior (like adding to the price).


* **Advantages:**
* **Highly Flexible:** You can mix and match decorators at runtime (e.g., a product can be both gift-wrapped AND have a warranty).
* **Follows Single Responsibility:** You can divide a complex class that has many possible behaviors into several smaller, focused decorator classes.



### **Code Implementation**

```java
// 1. The Interface
package org.example.structural.decorator;

public interface Product {
   double price();
   String description();
}

// 2. Base Concrete Class
class BasicProduct implements Product {
   @Override
   public double price() { return 500.0; }
   @Override
   public String description() { return "Basic T-Shirt"; }
}

// 3. The Decorator (Abstract)
abstract class ProductDecorator implements Product {
   protected Product decoratedProduct;

   public ProductDecorator(Product product) {
       this.decoratedProduct = product;
   }
}

// 4. Concrete Decorators
class GiftWrapDecorator extends ProductDecorator {
   public GiftWrapDecorator(Product product) { super(product); }

   @Override
   public double price() {
       return decoratedProduct.price() + 50.0; // Adding wrapping cost
   }

   @Override
   public String description() {
       return decoratedProduct.description() + " + Gift Wrapped";
   }
}

// Client Usage
// Product myOrder = new GiftWrapDecorator(new BasicProduct());
// System.out.println(myOrder.description() + " Cost: " + myOrder.price());

```
1. How is it implemented here?
   The magic of the Decorator pattern lies in Composition + Inheritance:

The Component (Product): This is the base interface. It ensures that both the "Plain" product and the "Decorated" product look the same to the user.

The Concrete Component (BasicProduct): This is the core object you are adding features to (e.g., a Phone or a Laptop).

The Decorator (ProductDecorator): This is the bridge. It is a Product (inheritance) and it has a Product (composition).

The Concrete Decorators (GiftWrap, ExtendedWarranty): These classes override the methods to add their own logic (adding price or description) before or after calling the original product's methods.

2. Why do we need the abstract class?
   The ProductDecorator abstract class is the "secret sauce" of this pattern for two main reasons:

Uniformity (The "Is-A" relationship): By implementing Product, the decorator ensures that a "GiftWrapped Laptop" is still treated as a Product. You can pass it into any method that expects a Product.

Delegation (The "Has-A" relationship): It holds a reference to the Product it is decorating. Without this abstract base, you would have to manually write the field protected Product product; and the constructor in every single decorator (GiftWrap, Warranty, Insurance, etc.). It keeps your code DRY (Don't Repeat Yourself).

Flexibility: It allows you to wrap a decorator inside another decorator because they all share the same parent type.
---

## **3. Composite Design Pattern**

* **When to Use:** Use this pattern when you need to treat individual objects and groups of objects (compositions) uniformly. It allows you to represent part-whole hierarchies.
* **Real-Life Examples:**
* **Product Bundles:** Treating a single "Smartphone" and a "Festival Bundle" (Smartphone + Case + Headphones) the same way in the shopping cart.
* **File System:** Treating a single "File" and a "Folder" (which contains files/folders) as the same type of object when calculating total size.


* **Implementation:**
* Define a **Component Interface** that both simple and complex objects implement.
* **Leaf Class:** Represents the individual objects (e.g., `Item`).
* **Composite Class:** Represents the collection of objects (e.g., `ProductBundle`) and contains a list of the Component types.


* **Advantages:**
* Simplifies client code because it doesn't need to check if it's dealing with a single item or a collection.
* Makes it easy to add new types of components.



### **Code Implementation**

```java
// 1. Common Interface
public interface ProductComponent {
    void showDetails();
    double getPrice();
}

// 2. Leaf (Individual Item)
class Item implements ProductComponent {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void showDetails() { System.out.println("Item: " + name); }
    public double getPrice() { return price; }
}

// 3. Composite (Bundle)
class ProductBundle implements ProductComponent {
    private List<ProductComponent> components = new ArrayList<>();

    public void addProduct(ProductComponent p) { components.add(p); }

    public void showDetails() {
        System.out.println("Bundle contains:");
        for(ProductComponent p : components) p.showDetails();
    }

    public double getPrice() {
        return components.stream().mapToDouble(ProductComponent::getPrice).sum();
    }
}

```

---

## **4. Facade Design Pattern**

* **When to Use:** Use this when you want to provide a simplified, high-level interface to a complex set of subsystems. It hides the "messy" internal details from the client.
* **Real-Life Examples:**
* **Order Checkout:** A client just calls `placeOrder()`, while the Facade handles Inventory check, Payment processing, and Shipping arrangement in the background.
* **Home Theater System:** A single "Play Movie" button that turns on the lights, starts the projector, and sets the sound system to the correct volume.


* **Implementation:**
* Create a **Facade Class** (e.g., `OrderFacade`) that contains instances of all the complex subsystem classes (`Inventory`, `Payment`, `Shipping`).
* The Facade provides a simple method that coordinates the calls to these subsystems in the correct order.


* **Advantages:**
* **Simplifies Usage:** Clients don't need to learn how 10 different subsystems work.
* **Decoupling:** If a subsystem changes (e.g., you switch shipping providers), you only change the Facade, not the client code.



### **Code Implementation**
To provide a **complete and working implementation** of the Facade Design Pattern, we will look at how an `OrderFacade` simplifies the interaction between three separate and complex subsystems: `Inventory`, `Payment`, and `Shipping`.

In this example, the client (the user) only needs to call one method, `placeOrder()`, and the Facade handles the sequence of checking stock, processing payment, and arranging delivery.

### **1. The Subsystems (Complex Logic)**

These classes represent the internal modules of your application. They are designed to do one thing well but can be complex to coordinate manually.

```java
package org.example.structural;

// Subsystem 1: Inventory Management
public class Inventory {
    public void updateStock(String item) {
        System.out.println("Inventory: Updating stock for " + item + " (Stock reduced by 1).");
    }
}

// Subsystem 2: Payment Processing
public class Payment {
    public void processPayment(double amount) {
        System.out.println("Payment: Charging $" + amount + " to the user's account.");
    }
}

// Subsystem 3: Shipping Service
public class Shipping {
    public void arrangeShipping(String item) {
        System.out.println("Shipping: Creating tracking ID and dispatching " + item + ".");
    }
}

```

### **2. The Facade Class (The Simplified Entry Point)**

This class wraps the subsystems. It provides a clean, high-level interface to the client.

```java
package org.example.structural.facade;

import org.example.structural.Inventory;
import org.example.structural.Payment;
import org.example.structural.Shipping;

public class OrderFacade {
    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;

    // Constructor initializes the internal subsystems
    public OrderFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
    }

    /**
     * This single method coordinates the entire workflow. 
     * The client doesn't need to know the order of execution.
     */
    public void placeOrder(String productName, double price) {
        System.out.println("--- Facade: Processing Order for " + productName + " ---");
        
        inventory.updateStock(productName);
        payment.processPayment(price);
        shipping.arrangeShipping(productName);
        
        System.out.println("--- Facade: Order successfully placed! ---");
    }
}

```

### **3. Client Usage**

Without the Facade, the client would have to instantiate all three classes and call them in the correct order. With the Facade, it's just one interaction.

```java
package org.example;

import org.example.structural.facade.OrderFacade;

public class SingletonMain {
    public static void main(String[] args) {
        // The client only interacts with the Facade
        OrderFacade orderFacade = new OrderFacade();

        // One simple call hides all the underlying complexity
        orderFacade.placeOrder("MacBook Pro M3", 2499.99);
    }
}

```

---

Here are the notes for the first major Behavioral Design Pattern: the **Strategy Pattern**, based on your documents.

---

## **1. Strategy Design Pattern**

* **When to Use:** Use this pattern when you have a family of algorithms or business rules (like different ways to calculate a discount) and you want to make them interchangeable at runtime. It allows the algorithm to vary independently from the clients that use it.
* **Real-Life Examples:**
* **E-commerce Discounts:** Applying different logic for a `SeasonalDiscount`, `LoyaltyDiscount`, or `NewUserDiscount` based on the user's profile or the time of year.
* **Payment Methods:** Choosing between different payment strategies like `CreditCardStrategy`, `UPIStrategy`, or `CashOnDelivery` at the checkout page.
* **Navigation Apps:** Selecting different pathfinding algorithms like "Shortest Path," "Fastest Path," or "Avoid Tolls."


* **Implementation:**
* Define a **Strategy Interface** (e.g., `DiscountStrategy`) with a common method (e.g., `applyDiscount`).
* Create **Concrete Strategy Classes** that implement the interface with specific logic.
* Create a **Context Class** (e.g., `ShoppingCart`) that contains a reference to the strategy interface and uses it to perform the calculation.


* **Advantages:**
* **Open/Closed Principle:** You can add new strategies (like a "Black Friday Discount") without changing the existing `ShoppingCart` code.
* **Eliminates Conditionals:** Replaces massive `if-else` or `switch` blocks with clean, polymorphic calls.



### **Code Implementation**

```java
// 1. The Strategy Interface
package org.example.behavioral;

public interface DiscountStrategy {
    double applyDiscount(double amount);
}

// 2. Concrete Strategies
class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90; // 10% off
    }
}

class LoyaltyDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.80; // 20% off
    }
}

// 3. The Context Class
public class ShoppingCart {
    private DiscountStrategy strategy;

    public ShoppingCart(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double checkout(double amount) {
        return strategy.applyDiscount(amount);
    }
}

// 4. Client Usage
// ShoppingCart cart = new ShoppingCart(new SeasonalDiscount());
// System.out.println("Price: " + cart.checkout(1000));

```

---

Here are the detailed notes for the **Observer Design Pattern** as requested.

---

## **2. Observer Design Pattern**

* **When to Use:** Use this pattern when a change in one object (the **Subject**) needs to be automatically communicated to multiple other objects (the **Observers**) without them being tightly coupled. It is perfect for implementing distributed event-handling systems.
* **Real-Life Examples:**
* **Order Status Notifications:** When an order changes from "Processed" to "Shipped," the system automatically notifies the `EmailService`, `SMSService`, and `PushNotificationService`.
* **Stock Market Apps:** Multiple display dashboards and alert systems are updated automatically whenever a specific stock price changes.
* **Social Media:** When a creator uploads a video, all subscribers are notified automatically.


* **Implementation:**
* Define an **Observer Interface** (e.g., `OrderObserver`) with an `update()` method.
* Create **Concrete Observers** (e.g., `EmailService`, `SMSService`) that implement the interface and define their specific reaction to the update.
* The **Subject** (e.g., `Order`) maintains a list of observers and provides methods to `attach()` or `detach()` them.
* When the Subject's state changes, it loops through the list and calls `update()` on every observer.


* **Advantages:**
* **Broadcast Communication:** Allows you to send notifications to an unknown number of objects.
* **Loose Coupling:** The Subject doesn't need to know the specific classes of the observers, only that they implement the interface.



### **Code Implementation**

```java
// 1. The Observer Interface
package org.example.behavioral;

public interface OrderObserver {
    void update(String status);
}

// 2. Concrete Observers
class EmailService implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Email Sent: Your order status is now " + status);
    }
}

class SMSService implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("SMS Sent: Order is " + status);
    }
}

// 3. The Subject (Order)
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;

    public void attach(OrderObserver observer) {
        observers.add(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}

// 4. Client Usage
// Order myOrder = new Order();
// myOrder.attach(new EmailService());
// myOrder.attach(new SMSService());
// myOrder.setStatus("SHIPPED"); 

```

---

Here are the notes for the **Command Design Pattern** based on your documents.

---

## **3. Command Design Pattern**

* **When to Use:** Use this pattern when you want to turn a request or an action into a stand-alone object. This allows you to parameterize clients with different requests, queue or log requests, and support undoable operations.
* **Real-Life Examples:**
* **Order Actions:** Representing operations like `PlaceOrder`, `CancelOrder`, or `ReturnOrder` as separate objects. This allows a history of actions to be kept for each customer.
* **Remote Controls:** A universal remote where each button is programmed with a "Command" object. The remote doesn't know what the button does; it just calls `execute()`.
* **Transaction Systems:** Keeping a log of banking transactions so that if a system fails, the commands can be replayed or undone.


* **Implementation:**
* Define a **Command Interface** (e.g., `Command`) with an `execute()` method and optionally an `undo()` method.
* Create **Concrete Command Classes** (e.g., `PlaceOrderCommand`) that wrap a receiver (the service that actually does the work) and implement the interface.
* The **Invoker** (e.g., a Button or a Menu) holds the command and calls `execute()` when triggered.
* The **Receiver** (e.g., `OrderService`) contains the actual business logic to perform the action.


* **Advantages:**
* **Undo/Redo Support:** Since actions are objects, you can easily store them in a stack to reverse them.
* **Decoupling:** The object that triggers the action is completely decoupled from the object that performs the action.



### **Code Implementation**

```java
// 1. The Command Interface
package org.example.behavioral;

public interface Command {
    void execute();
    void undo(); // Optional: used for reversing the action
}

// 2. The Receiver (The class that knows how to do the work)
class OrderService {
    public void placeOrder() {
        System.out.println("Order has been placed successfully.");
    }
    public void cancelOrder() {
        System.out.println("Order has been cancelled.");
    }
}

// 3. Concrete Command
public class PlaceOrderCommand implements Command {
    private OrderService orderService;

    public PlaceOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        orderService.placeOrder();
    }

    @Override
    public void undo() {
        orderService.cancelOrder();
    }
}

// 4. Client Usage
// OrderService service = new OrderService();
// Command placeOrder = new PlaceOrderCommand(service);
// placeOrder.execute(); // Places the order
// placeOrder.undo();    // Cancels the order

```

---

Here are the notes for the final behavioral pattern from your documents, the **Mediator Design Pattern**.

---

## **4. Mediator Design Pattern**

* **When to Use:** Use this pattern to reduce complex, direct dependencies between multiple classes (often called "spaghetti code"). It centralizes communication, so instead of objects talking to each other directly, they only talk to a central **Mediator** object.
* **Real-Life Examples:**
* **E-commerce Order Processing:** Instead of the `Inventory` class directly calling the `Payment` class, and `Payment` calling `Shipping`, an `OrderProcessor` acts as the mediator to coordinate the entire workflow.
* **Air Traffic Control (ATC):** Pilots of different airplanes don't talk to each other to coordinate landings; they all talk to the ATC (the Mediator), which directs them.
* **Chat Room:** Users in a chat room don't send messages directly to each other's devices; they send the message to the chat server (Mediator), which then distributes it to the right recipients.


* **Implementation:**
* Define a **Mediator Interface** (e.g., `OrderMediator`) that defines the contract for communication.
* Create a **Concrete Mediator** (e.g., `OrderProcessor`) that holds references to all the subsystems (Inventory, Payment, Shipping).
* The subsystems (Colleagues) perform their specific tasks but rely on the Mediator to trigger the next step in the process.


* **Advantages:**
* **Decoupling:** Modules like `Inventory` and `Shipping` remain completely independent of each other.
* **Centralized Control:** The entire business workflow is in one place (`OrderProcessor`), making it easier to modify or debug.



### **Code Implementation**


### **1. The Subsystems (Colleague Classes)**

These classes focus only on their specific logic. They do not know about the existence of the other subsystems.

```java
// Subsystem 1: Inventory
public class Inventory {
    public void updateStock(String item) {
        System.out.println("Inventory: Checking and updating stock for " + item);
    }
}

// Subsystem 2: Payment
public class Payment {
    public void processPayment(double amount) {
        System.out.println("Payment: Processing transaction of $" + amount);
    }
}

// Subsystem 3: Shipping
public class Shipping {
    public void arrangeShipping(String item) {
        System.out.println("Shipping: Dispatching " + item + " to the delivery partner.");
    }
}

```

### **2. The Mediator Interface**

This defines the contract for how communication should be handled.

```java
public interface OrderMediator {
    void processOrder(String item, double amount);
}

```

### **3. The Concrete Mediator (The Hub)**

This class encapsulates the coordination logic. It holds references to all subsystems and dictates the flow of the process.

```java
public class OrderProcessor implements OrderMediator {
    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;

    // The Mediator initializes or receives the subsystems
    public OrderProcessor() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
    }

    @Override
    public void processOrder(String item, double amount) {
        System.out.println("--- Mediator: Starting Order Workflow ---");
        
        // Step 1: Manage Inventory
        inventory.updateStock(item);
        
        // Step 2: Handle Payment
        payment.processPayment(amount);
        
        // Step 3: Arrange Shipping
        shipping.arrangeShipping(item);
        
        System.out.println("--- Mediator: Workflow Finished Successfully ---");
    }
}

```

### **4. Client Usage**

The client doesn't need to know the order of operations or how the three subsystems interact. It only talks to the Mediator.

```java
public class SingletonMain {
    public static void main(String[] args) {
        // Create the Mediator
        OrderMediator orderMediator = new OrderProcessor();
        
        // Execute a complex process with one simple call
        orderMediator.processOrder("Smartphone", 10000.00);
    }
}

```
While both **Facade** and **Mediator** are used to organize complex interactions between classes, they serve different structural purposes. The primary difference lies in the **direction of communication** and the **intent** behind the pattern.

---

### **1. Structural Comparison**

| Feature | **Facade Design Pattern** | **Mediator Design Pattern** |
| --- | --- | --- |
| **Category** | Structural Pattern | Behavioral Pattern |
| **Direction** | **One-Way:** The client calls the Facade, which then calls subsystems. | **Two-Way:** Colleagues talk to the Mediator, and the Mediator talks back to them. |
| **Intent** | To provide a **simplified interface** to a complex subsystem. | To **decouple** multiple objects that need to communicate with each other. |
| **Control** | Subsystems are generally unaware of the Facade. | Colleagues are aware of the Mediator and rely on it for communication. |
| **Complexity** | Simplifies the "view" of a system. | Centralizes the "logic" of interactions. |

---

### **2. Facade Pattern: The "Simplified View"**

In your e-commerce document, the `OrderFacade` is used to hide the complexity of internal systems.

* **The Flow:** The Client → `OrderFacade` → (`Inventory`, `Payment`, `Shipping`).
* **Why use it:** The client just wants to "Place Order." They don't care about the 10 steps happening inside.
* **Key Detail:** The `Inventory` or `Payment` classes don't know the Facade exists; they just do their job when called.

---

### **3. Mediator Pattern: The "Central Hub"**

The `OrderProcessor` in your behavioral document acts as a Mediator to manage the workflow.

* **The Flow:** `Inventory` ↔ `Mediator` ↔ `Payment` ↔ `Mediator` ↔ `Shipping`.
* **Why use it:** To prevent "Spaghetti Code." If `Inventory` needed to trigger `Payment` directly, and `Payment` needed to trigger `Shipping`, they would all be tightly coupled.
* **Key Detail:** The subsystems (Colleagues) often communicate through the mediator to decide what happens next in the business logic.

---

### **4. Working Code Comparison**

#### **Facade Implementation (Structural)**

The client is outside the system looking in.

```java
public class OrderFacade {
    public void placeOrder(String item, double price) {
        inventory.check(item); // Step 1
        payment.pay(price);    // Step 2
        shipping.ship(item);   // Step 3
    }
}

```

#### **Mediator Implementation (Behavioral)**

The mediator is inside the system, coordinating the players.

```java
public class OrderProcessor implements OrderMediator {
    // When a Colleague triggers an event, the mediator decides the next move
    public void onEvent(String eventType) {
        if (eventType.equals("PAYMENT_SUCCESS")) {
            shipping.arrangeShipping();
        } else if (eventType.equals("OUT_OF_STOCK")) {
            notification.sendAlert();
        }
    }
}

```

### **Summary Note**

* Use **Facade** when you want to make a complex library **easier to use**.
* Use **Mediator** when you want to make complex communication between objects **easier to maintain**.


While both patterns are "Creational" or "Structural" in nature, they solve fundamentally different problems: the **Builder** is about *how* an object is made, whereas the **Decorator** is about *how* an object's behavior is extended after it's made.

---

### 1. Builder Pattern (The "Architect")

The Builder pattern is a **Creational** pattern used to construct complex objects step-by-step. It is most useful when an object has many optional parameters or requires a multi-stage assembly.

* **Focus:** Creation and Encapsulation.
* **Problem it solves:** Avoiding "Telescoping Constructors" (where you have a constructor with 10+ parameters, many of which are null).
* **Result:** You get a single, fully formed object at the end of the `build()` process.

**Example:** Building a Pizza. You choose the crust, then the sauce, then the toppings. Once it's built, the process is over.

---

### 2. Decorator Pattern (The "Attachments")

The Decorator pattern is a **Structural** pattern used to add new functionality to an existing object dynamically without altering its structure. It wraps the original object.

* **Focus:** Extension and Flexibility.
* **Problem it solves:** Avoiding a "Class Explosion" (where you create endless subclasses for every possible combination of features).
* **Result:** You get a "wrapped" object that still looks like the original but has extra "layers" of behavior.

**Example:** Ordering a Coffee. You have a basic Coffee object. You wrap it with a "Milk Decorator," then wrap that with a "Sugar Decorator." It’s still a Coffee, but the `cost()` method now calculates the total of all layers.

---

### Comparison Table

| Feature | Builder Pattern | Decorator Pattern |
| --- | --- | --- |
| **Category** | Creational (How to build) | Structural (How to add features) |
| **Object State** | Built in stages; returned when "done." | Exists immediately; features added at runtime. |
| **Interface** | Builder methods often return the Builder (`this`). | Decorators must implement the same interface as the object they wrap. |
| **Complexity** | Hides the complexity of internal construction. | Adds behavior without changing the internal code. |
| **Visibility** | The client is involved in the step-by-step construction. | The client often doesn't know the object is "decorated." |

### Key Distinction

> Use **Builder** when you want to simplify the creation of a complex object. Use **Decorator** when you want to add new responsibilities to an object dynamically without using inheritance.

