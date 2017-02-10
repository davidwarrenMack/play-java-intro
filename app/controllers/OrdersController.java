package controllers;
import models.MyOrder;
import models.Orders;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.List;
import static play.libs.Json.toJson;


public class OrdersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public OrdersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result getOrders()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().
                createQuery("select orderId, employeeId, customerId, freight, shipCity, shipCountry from Orders").getResultList();

        return ok(toJson(orders));
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().
                createQuery("select o from Orders o").getResultList();

        return ok(views.html.orders.render(orders));
    }
    @Transactional(readOnly = true)
    public Result indexOfStuff()
    {
        List<MyOrder> orders = (List<MyOrder>) jpaApi.em().createNativeQuery("select od.orderId, od.productid, o.customerId, od.quantity from orders o join orderdetails od on o.orderid = od.orderid", MyOrder.class).getResultList();

        return ok(views.html.myorder.render(orders));
    }



}
