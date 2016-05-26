package me.mhn.money

import scala.language.implicitConversions

class Conversion(
  val amount: Money,
  val rate: Rate
)

object Conversion {
  implicit def conversionToMoney(conversion: Conversion): Money = Conversion.convert(conversion)

  def convert(conversion: Conversion) = {
    val fractionDiff = conversion.rate.pair.right.fraction - conversion.rate.pair.left.fraction
    val exchangedAmount = (conversion.amount.amount * conversion.rate.rate) * math.pow(10, fractionDiff)
    new Money(conversion.rate.pair.right, exchangedAmount.floor.toInt)
  }
}
