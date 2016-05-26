package me.mhn.money

import scala.language.implicitConversions

class Conversion[F <: Currency](
  val amount: Money[F],
  val rate: Rate[F, Currency]
)

object Conversion {
  implicit def conversionToMoney(conversion: Conversion[_ <: Currency]): Money[Currency] = Conversion.convert(conversion)

  def convert(conversion: Conversion[_ <: Currency]) = {
    val fractionDiff = conversion.rate.pair.right.fraction - conversion.rate.pair.left.fraction
    val exchangedAmount = (conversion.amount.amount * conversion.rate.rate) * math.pow(10, fractionDiff)
    new Money(conversion.rate.pair.right, exchangedAmount.floor.toInt)
  }
}
