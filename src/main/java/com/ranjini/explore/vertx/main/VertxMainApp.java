package com.ranjini.explore.vertx.main;

import com.ranjini.explore.vertx.verticle.OrderVerticle;
import com.ranjini.explore.vertx.verticle.PaymentVerticle;
import io.vertx.core.Vertx;

public class VertxMainApp {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new OrderVerticle());
        vertx.deployVerticle(new PaymentVerticle());
    }
}