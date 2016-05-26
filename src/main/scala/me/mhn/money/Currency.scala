package me.mhn.money

import scala.language.implicitConversions

sealed trait Currency {
  val code: String
  val fraction: Int

  def / (other: Currency) = CurrencyPair(this, other)
  def | (amount: Int) = Money(this, amount)
}

object Currency extends Enumeration {
  val GBP, USD, EUR, JPY = Value

  private val currencyData = Map(
    GBP -> ("GBP", 2),
    USD -> ("USD", 2),
    EUR -> ("EUR", 2),
    JPY -> ("JPY", 0)
  )

  implicit def valueToCurrency(value: Value): Currency = Currency(value)

  def apply(currency: Value): Currency = new RealCurrency(code(currency), fraction(currency))
  def apply(code: String, fraction: Int): Currency = new RealCurrency(code, fraction)

  def code(currency: Value): String = currencyData(currency)._1
  def fraction(currency: Value): Int = currencyData(currency)._2

  private class RealCurrency(val code: String, val fraction: Int) extends Currency {
    override def equals(that: scala.Any): Boolean = that match {
      case that: Currency => that.code == code && that.fraction == fraction
      case _ => super.equals(that)
    }

    override def toString = code
  }
}
