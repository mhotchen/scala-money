package me.mhn.money

object Test extends App {
  val amountUsd = Money(Currency(Currency.Usd), 10000)
  val moreAmountUsd = amountUsd + Money(Currency(Currency.Usd), 10000)

  val usdToGbp = Rate(Currency(Currency.Usd), Currency(Currency.Gbp), 0.6837)
  val usdToJpy = Rate(Currency(Currency.Usd), Currency(Currency.Jpy), 109.99)
  val jpyToUsd = usdToJpy.invert

  val amountGbp = Converter.convert(amountUsd, usdToGbp)
  val amountJpy = Converter.convert(amountUsd, usdToJpy)
  val amountJpy2 = Converter.convert(amountUsd, usdToJpy.invert.invert)
  val moreAmountGbp = Converter.convert(amountUsd + moreAmountUsd, usdToGbp)
  val amountUsd2 = Converter.convert(amountJpy, jpyToUsd)
  val amountJpy3 = Converter.convert(Converter.convert(amountJpy, jpyToUsd), usdToJpy)

  println(jpyToUsd)
  println(usdToJpy)

  println(amountUsd)
  println(amountGbp)
  println(amountJpy)
  println(amountJpy2)
  println(amountJpy3)
  println(moreAmountUsd)
  println(moreAmountGbp)
  println(Currency.code(Currency.Gbp))
}
