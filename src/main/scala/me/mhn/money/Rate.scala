package me.mhn.money

case class Rate[F <: Currency, T <: Currency](pair: CurrencyPair[F, T], rate: Double) {
  lazy val invert = Rate(pair.invert, 1 / rate)
  override def toString = pair + ": " + rateFormatted

  lazy val rateFormatted: String = rate.formatted(if (rate < 1) "%.6f" else "%.4f")
}
