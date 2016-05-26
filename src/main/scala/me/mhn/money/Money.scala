package me.mhn.money

case class Money(
  currency: Currency,
  amount: Long
) {

  lazy val amountFormatted: String = currency.fraction match {
    case 0 => "%d" format amount
    case f => "%." + f + "f" format amount / math.pow(10, f)
  }

  override def toString = currency.toString + " " + amountFormatted

  def + (that: Money) = new Money(currency, amount + that.amount)
  def + (i: Long) = new Money(currency, amount + i)

  def - (that: Money) = new Money(currency, amount - that.amount)
  def - (i: Long) = new Money(currency, amount - i)

  def * (rate: Rate): Money = new Conversion(this, rate)
}
