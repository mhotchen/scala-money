package me.mhn.money

object Test extends App {
  val usd = Currency.USD
  val gbp = Currency.GBP
  val jpy = Currency.JPY

  val amountUsd  = usd|10000
  val amountUsd2 = amountUsd + 10000

  val usdToGbp  = (usd/gbp)-> 0.6837
  val usdToJpy  = (usd/jpy)-> 109.99
  val jpyToUsd  = usdToJpy.invert
  val usdToJpy2 = jpyToUsd.invert.invert.invert

  val amountGbp  = amountUsd * usdToGbp
  val amountJpy  = amountUsd * usdToJpy
  val amountJpy2 = amountUsd * usdToJpy2.invert.invert

  // from GBP to JPY via USD using an inverted USD/GBP rate
  val amountJpy3 = amountGbp * usdToGbp.invert * usdToJpy

  println("Rates")
  println(usdToGbp)
  println(usdToGbp.invert)
  println(usdToGbp.invert.invert)
  println(usdToJpy)
  println(jpyToUsd)
  println(usdToJpy2)

  println("Amounts")
  println(amountGbp)
  println(amountJpy)
  println(amountJpy2)
  println(amountJpy3)

  println("Currencies")
  println(gbp + " has " + gbp.fraction + " fractional units")
  println(Currency.EUR + " has " + Currency.EUR.fraction + " fractional units")

  println("Currency pairs")
  println(gbp/usd)
  println(Currency.EUR/Currency.JPY)
}
