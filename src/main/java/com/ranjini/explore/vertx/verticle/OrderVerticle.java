package com.ranjini.explore.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class OrderVerticle extends AbstractVerticle {

    @Override
    public void start() {

        vertx.createHttpServer()
                .requestHandler(req -> {

                    if (req.path().equals("/order")) {

                        JsonObject order = new JsonObject()
                                .put("orderId", 123)
                                .put("amount", 250);

                        // Send to payment service via event bus
                        vertx.eventBus().request("payment.process", order, reply -> {
                            if (reply.succeeded()) {
                                req.response().end("Order processed: " + reply.result().body());
                            } else {
                                req.response().setStatusCode(500).end("Failed");
                            }
                        });

                    } else {
                        req.response().end("Welcome");
                    }

                })
                .listen(8080);
    }

    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new OrderVerticle());
    }
}