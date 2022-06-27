package ru.netology;

import java.io.*;
import java.util.concurrent.ExecutionException;

public class Main {
  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

    new Server().start();
  }
}


