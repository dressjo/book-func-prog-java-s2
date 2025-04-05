package org.example;

import static org.example.Expr.diff;

import org.example.Expr.ConstExpr;
import org.example.Expr.ProdExpr;

public class Expression {

  public static void main(String[] args) {
    System.out.println(Expr.eval(new ProdExpr(new ConstExpr(2), new ConstExpr(3))));

    final Expr diff = diff(new ProdExpr(new ConstExpr(2), new ConstExpr(3)));
    System.out.println(Expr.eval(diff));

  }
}

sealed interface Expr {
  record SumExpr(Expr left, Expr right) implements Expr {}
  record ProdExpr(Expr left, Expr right) implements Expr {}
  record NegExpr(Expr expr) implements Expr {}
  record ConstExpr(int value) implements Expr {}

  static int eval(Expr expr) {
    return switch(expr) {
      case SumExpr(var left, var right) -> eval(left) + eval(right);
      case ProdExpr(var left, var right) -> eval(left) * eval(right);
      case NegExpr(var val) -> -eval(expr);
      case ConstExpr(var value) -> value;
    };
  }

  static Expr diff(Expr e) {
    return switch(e) {
      case SumExpr(var left, var right) -> new SumExpr(diff(left), diff(right));
      case ProdExpr(var left, var right) -> new SumExpr(new ProdExpr(diff(left), right), new ProdExpr(left, diff(right)));
      case NegExpr(var val) -> new NegExpr(diff(val));
      case ConstExpr(var value) -> new ConstExpr(0);
    };
  }





}
