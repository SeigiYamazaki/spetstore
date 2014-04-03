package com.github.j5ik2o.spetstore.domain.model.customer

import com.github.j5ik2o.spetstore.domain.infrastructure.support.{EntityIOContext, Entity}
import com.github.j5ik2o.spetstore.domain.lifecycle.customer.CustomerRepository
import com.github.j5ik2o.spetstore.domain.model.basic.{StatusType, SexType}
import com.github.j5ik2o.spetstore.domain.model.item.ItemId
import com.github.j5ik2o.spetstore.domain.model.purchase.{OrderId, Order, Cart, CartItem}
import scala.util.Try
import com.github.j5ik2o.spetstore.infrastructure.identifier.IdentifierService

/**
 * ペットストアの顧客を表すエンティティ。
 *
 * @param id 識別子
 * @param status [[StatusType]]
 * @param name 名前
 * @param sexType 性別
 * @param profile [[com.github.j5ik2o.spetstore.domain.model.customer.CustomerProfile]]
 * @param config [[com.github.j5ik2o.spetstore.domain.model.customer.CustomerConfig]]
 */
case class Customer
(id: CustomerId,
 status: StatusType.Value,
 name: String,
 sexType: SexType.Value,
 profile: CustomerProfile,
 config: CustomerConfig)
  extends Entity[CustomerId] {

  def addCartItem(cart: Cart, cartItem: CartItem): Cart =
    cart.addCartItem(cartItem)

  def removeCartItemByItemId(cart: Cart, itemId: ItemId): Cart =
    cart.removeCartItemByItemId(itemId)

  def newOrderFromCart(orderId: OrderId, cart: Cart)
                      (implicit is: IdentifierService, 
                       cr: CustomerRepository, ctx: EntityIOContext): Try[Order] =
    Order.fromCart(orderId, cart)

}




