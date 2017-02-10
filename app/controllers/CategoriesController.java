package controllers;

import models.Categories;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.List;
import static play.libs.Json.toJson;

public class CategoriesController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public  CategoriesController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result getCategories()
    {
        List<Categories> categories = (List<Categories>) jpaApi.em().
                createQuery("select categoryId, categoryName, description, picture from Categories").getResultList();

        return ok(toJson(categories));
    }

    @Transactional(readOnly = true)
    public Result index()
    {
        List<Categories> categories = (List<Categories>) jpaApi.em().
                createQuery("select c from Categories c").getResultList();

        return ok(views.html.categories.render(categories));
    }
    @Transactional(readOnly = true)
    public Result newCategory()
    {
        return ok(views.html.newcategories.render());
    }

    @Transactional
    public Result addCategory()
    {
        //Map the data from the html form into an instance of the Categories model
        //Categories.class lets the form factory create an instance of Categories
        //Values from the form are pushed into the model based on name of form field match name of variable in model

        Categories category = formFactory.form(Categories.class).bindFromRequest().get();

        //Save the new category to the database
        jpaApi.em().persist(category);

        //Send the browser to the list of categories page
        return redirect(routes.CategoriesController.index());
    }

    @Transactional(readOnly = true)
    public Result getPicture(Long id)
    {
        Categories category = (Categories) jpaApi.em().
                createQuery("select c from Categories c where categoryId = :id").
                setParameter("id", id).getSingleResult();

        if(category.picture == null)
        {
            return null;
        }
        else
        {
            return ok(category.picture).as("image/bmp");
        }
    }

    @Transactional (readOnly = true)
    public Result editCategory(Long categoryId)
    {
        Categories category = (Categories) jpaApi.em().
                createQuery("select c from Categories c where categoryId = :id")
                .setParameter("id", categoryId).getSingleResult();

        return  ok(views.html.updateCategory.render(category));

    }

    @Transactional
    public Result updateCategory()
    {
        //gets the data from the form the user submitted
        DynamicForm postedForm = formFactory.form().bindFromRequest();

        //copy the form values out into local variables
        Long categoryId = new Long(postedForm.get("categoryId"));
        String categoryName = postedForm.get("categoryName");
        String categoryDescription = postedForm.get("categoryDescription");

        //get the model of the category we want to update
        Categories category = (Categories)jpaApi.em()
                .createQuery("select c from Categories c where categoryId = :id")
                .setParameter("id", categoryId).getSingleResult();

        //copy new values into model
        category.categoryName = categoryName;
        category.description = categoryDescription;

        //save the model to the databases
        jpaApi.em().persist(category);

        //send the user to the list of categories view
        return redirect(routes.CategoriesController.index());
    }

}
