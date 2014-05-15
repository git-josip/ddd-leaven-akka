package ecommerce.sales.domain.reservation

import test.support.{LocalPublisher, EventsourcedAggregateRootSpec}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import ecommerce.sales.domain.reservation.Reservation.ReserveProduct
import ddd.support.domain.error.AggregateRootNotInitializedException
import ddd.support.domain.Office._
import test.support.TestConfig._
import ddd.support.domain.AggregateRootActorFactory
import ecommerce.sales.domain.inventory.Product
import scala.Product
import infrastructure.actor.PassivationConfig
import akka.actor.Props

class ReservationFailuresSpec extends EventsourcedAggregateRootSpec[Reservation](testSystem) {

  import ReservationSpec.ReservationActorFactory

  "Reservation of product" must {
    "fail if Reservation does not exist" in {
      val reservationId = "reservation1"
      implicit val timeout = Timeout(5, SECONDS)

      // Use ask (?) to send a command and expect Failure(AggregateRootNotInitializedException) in the response
      expectFailure[AggregateRootNotInitializedException] {
        office[Reservation] ? ReserveProduct(reservationId, "product1", 1)
      }
    }
  }

}
