package com.ranjini.explore.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class PaymentVerticle extends AbstractVerticle {

    @Override
    public void start() {

        vertx.eventBus().consumer("payment.process", message -> {

            JsonObject order = (JsonObject) message.body();

            // Simulate payment processing
            int amount = order.getInteger("amount");

            String result = "Payment successful for amount: " + amount;

            // Reply back
            message.reply(result);
        });
    }
}
