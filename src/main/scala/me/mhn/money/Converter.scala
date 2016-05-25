package me.mhn.money

object Converter {
  def convert[F <: Currency, T <: Currency](
    amount: Money[F],
    rate: Rate[F, T]
  ): Money[T] = {
    val fractionDiff = rate.to.fraction - rate.from.fraction
    val exchangedAmount = (amount.amount * rate.rate) * math.pow(10, fractionDiff)
    new Money(rate.to, exchangedAmount.floor.toInt)
  }
}
