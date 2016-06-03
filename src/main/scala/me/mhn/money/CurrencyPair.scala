package me.mhn.money

case class CurrencyPair(left: Currency, right: Currency) {
  lazy val invert = new CurrencyPair(right, left)
  override def toString = left + "/" + right

  def is(rate: Double) = new Rate(this, rate)
}
