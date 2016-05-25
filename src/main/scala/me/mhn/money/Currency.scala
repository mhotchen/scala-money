package me.mhn.money

sealed trait Currency {
  val code: String
  val fraction: Int
}

object Currency extends Enumeration {
  val Gbp, Usd, Eur, Jpy = Value

  private val currencyData = Map(
    Gbp -> ("GBP", 2),
    Usd -> ("USD", 2),
    Eur -> ("EUR", 2),
    Jpy -> ("JPY", 0)
  )

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
