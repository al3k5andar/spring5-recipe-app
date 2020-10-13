package com.am.spring.recipespringboot.bootstrap;

import com.am.spring.recipespringboot.domain.*;
import com.am.spring.recipespringboot.repositories.CategoryRepository;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(loadData());
    }

    private List<Recipe> loadData() {
        log.debug("Starting data loading");
        List<Recipe> recipeList= new ArrayList<>(2);

//        Units of Measures
        Optional<UnitOfMeasure> teaspoonOptional= unitOfMeasureRepository.findByDescription("Teaspoon");
        if(!teaspoonOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> tablespoonOptional= unitOfMeasureRepository.findByDescription("Tablespoon");
        if(!tablespoonOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> cupOptional= unitOfMeasureRepository.findByDescription("Cup");
        if(!cupOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> pinchOptional= unitOfMeasureRepository.findByDescription("Pinch");
        if(!pinchOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> ounceOptional= unitOfMeasureRepository.findByDescription("Ounce");
        if(!ounceOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> eachOptional= unitOfMeasureRepository.findByDescription("Each");
        if(!eachOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> dashOptional= unitOfMeasureRepository.findByDescription("Dash");
        if(!dashOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

        Optional<UnitOfMeasure> pintOptional= unitOfMeasureRepository.findByDescription("Pint");
        if(!pintOptional.isPresent())
            throw new RuntimeException("Expected UOM is not found!");

//      Get Optionals
        UnitOfMeasure teaspoon= teaspoonOptional.get();
        UnitOfMeasure tablespoon= tablespoonOptional.get();
        UnitOfMeasure cup= cupOptional.get();
        UnitOfMeasure pinch= pinchOptional.get();
        UnitOfMeasure ounce= ounceOptional.get();
        UnitOfMeasure each= eachOptional.get();
        UnitOfMeasure dash= dashOptional.get();
        UnitOfMeasure pint= pintOptional.get();

//      Get Categories
        Optional<Category> americanOptional= categoryRepository.findByDescription("American");
        if(!americanOptional.isPresent())
            throw new RuntimeException("Expected Category is not found!");

        Optional<Category> mexicanOptional= categoryRepository.findByDescription("Mexican");
        if(!mexicanOptional.isPresent())
            throw new RuntimeException("Expected Category is not found!");

        Category american= americanOptional.get();
        Category mexican= mexicanOptional.get();

//        Guacamole Recipe
        Recipe guacamole= new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(0);
        guacamole.setDifficulty(Difficulty.EASY);


        Notes guacamoleNotes= new Notes();
        guacamoleNotes.setRecipeNotes("Once you have basic guacamole down, feel free to experiment with variations including strawberries," +
                " peaches, pineapple, mangoes, even watermelon. " +
                "One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). " +
                "You can get creative with homemade guacamole!\n" +
                "\n" +
                "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt." +
                " Don’t let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Don’t have enough avocados? To extend a limited supply of avocados, add either sour cream or" +
                " cottage cheese to your guacamole dip." +
                " Purists may be horrified, but so what? It tastes great.");
        guacamole.setNotes(guacamoleNotes);

        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. " +
                "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. " +
                "(See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. " +
                "(Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. " +
                "The acid in the lime juice will provide some balance to the richness of the avocado and will help" +
                " delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles." +
                " Chili peppers vary individually in their hotness. " +
                "So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead," +
                " place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it." +
                " (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        guacamole.addIngredient(new Ingredient("ripe avocado",new BigDecimal(2),each));
        guacamole.addIngredient(new Ingredient("salt",new BigDecimal("0.4"),tablespoon));
        guacamole.addIngredient(new Ingredient("fresh lime juice", new BigDecimal(1), tablespoon));
        guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion",
                new BigDecimal(2),tablespoon));
        guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced",
                new BigDecimal(2),each));
        guacamole.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped",
                new BigDecimal(2), tablespoon));
        guacamole.addIngredient(new Ingredient("freshly grated black pepper",new BigDecimal(1),dash));
        guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                new BigDecimal(2), each));

        guacamole.getCategories().add(american);
        guacamole.getCategories().add(mexican);

        recipeList.add(guacamole);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacosRecipe.setNotes(tacoNotes);


        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoon));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), each));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoon));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), each));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cup));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), each));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), each));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pint));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), each));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), each));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), each));

        tacosRecipe.getCategories().add(american);
        tacosRecipe.getCategories().add(mexican);

        recipeList.add(tacosRecipe);

        log.debug("Finish data Loading");

        return recipeList;
    }
}
