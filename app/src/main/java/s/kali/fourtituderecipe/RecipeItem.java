package s.kali.fourtituderecipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

class RecipeItem {
    
    private int recipeId;
    private String recipeTitle;
    private String recipeImgPath;
    private String recipeType;
    private String recipeIngre;
    private String recipeSteps;
    
    
    RecipeItem(String title){
        recipeTitle = title;
    }
    RecipeItem(int id, String title, String img, String type, String ingre, String steps){
        recipeId = id;
        recipeTitle = title;
        recipeImgPath = img;
        recipeType = type;
        recipeIngre = ingre;
        recipeSteps = steps;
    }
    
    RecipeItem(String title, String img, String type, String ingre, String steps){
        recipeId = -1;
        recipeTitle = title;
        recipeImgPath = img;
        recipeType = type;
        recipeIngre = ingre;
        recipeSteps = steps;
    }
    
    public String getRecipeTitle() {
        return recipeTitle;
    }
    
    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }
    
    public String getRecipeImgPath() {
        return recipeImgPath;
    }
    
    public void setRecipeImgPath(String recipeImgPath) {
        this.recipeImgPath = recipeImgPath;
    }
    
    public String getRecipeIngre() {
        return recipeIngre;
    }
    
    public void setRecipeIngre(String recipeIngre) {
        this.recipeIngre = recipeIngre;
    }
    
    public String getRecipeSteps() {
        return recipeSteps;
    }
    
    public void setRecipeSteps(String recipeSteps) {
        this.recipeSteps = recipeSteps;
    }
    
    String getRecipeType() {
        return recipeType;
    }
    
    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }
    
    
    public int getRecipeId() {
        return recipeId;
    }
    
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public static final int getRes(String imgPath){
        int rid;
        if(imgPath != null) {
            switch (imgPath) {
                case "b":
                    rid = s.kali.fourtituderecipe.R.drawable.b;
                    break;
                case "c":
                    rid = R.drawable.c;
                    break;
                case "d":
                    rid = R.drawable.d;
                    break;
                case "e":
                    rid = R.drawable.e;
                    break;
                case "f":
                    rid = R.drawable.f;
                    break;
                case "g":
                    rid = R.drawable.g;
                    break;
                case "h":
                    rid = R.drawable.h;
                    break;
                case "i":
                    rid = R.drawable.i;
                    break;
                case "j":
                    rid = R.drawable.j;
                    break;
                case "k":
                    rid = R.drawable.k;
                    break;
                default:
                    rid = R.drawable.ic_error;
            }
        }else{
            rid = R.drawable.ic_error;
        }
        return rid;
    }
//    public static final Bitmap getBitmap(String path){
//        File imgFile = new File(path);
//        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//        return (myBitmap);
////        return null;
//    }
    public static final ArrayList<RecipeItem> init(String[] r){
        ArrayList<RecipeItem> arcp = new ArrayList<RecipeItem>();
        RecipeItem rcp = new RecipeItem("Broccoli and Everything Salad",
                "b",
                r[1]/*Salad*/,
                "3 cups raw broccoli, chopped\n" +
                        "1 medium carrot, peeled and diced\n" +
                        "2 stalks celery, thinly sliced\n" +
                        "1⁄2 cup raisins\n" +
                        "1⁄4 cup onion, chopped\n" +
                        "1 cup cooked ham, chicken or turkey\n" +
                        "1⁄4 cup light mayonnaise\n" +
                        "1⁄2 cup plain, nonfat yogurt\n" +
                        "1 Tablespoon sugar\n" +
                        "1 teaspoon vinegar",
            
                "    Wash and prepare vegetables.\n" +
                        "    In a large bowl mix together broccoli, carrot, celery, raisins, onion and meat.\n" +
                        "    Mix together mayonnaise, yogurt, sugar and vinegar in a separate bowl.\n" +
                        "    Add mayonnaise mixture to salad and mix well.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    Try adding apples or jicama.\n" +
                        "    You can use dried cranberries instead of raisins.\n" +
                        "    This salad can be prepared the day before and stored in the refrigerator.");
        arcp.add(rcp);
    
    
        arcp.add(new RecipeItem("Mediterranean Tuna Salad",
                "c",
                r[1]/*salad*/,
                "3 cans (5 ounces each) tuna in water, drained\n" +
                        "1 cup carrot, peeled and coarsely grated (about 2 medium carrots)\n" +
                        "2 cups diced cucumber\n" +
                        "1 1⁄2 cups peas, canned and drained or thawed from frozen\n" +
                        "3⁄4 cup low-fat Italian salad dressing",
            
                "    Place drained tuna in a medium bowl. Use a fork to break apart chunks of tuna.\n" +
                        "    Add carrot, cucumber, peas and salad dressing. Mix well.\n" +
                        "    Serve immediately or make ahead, cover and refrigerate until ready to serve.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    Serve on lettuce leaves or make sandwiches with whole wheat or pocket (pita) bread.")
        );
        arcp.add(new RecipeItem("Cowboy Salad",
                "d",
                r[1]/*Salad*/,
                "2 cans (15 ounces) black-eyed peas or black beans (try a mix, or other types)\n" +
                        "1 1⁄2 cups corn (canned and drained, frozen, or fresh cooked)\n" +
                        "1 bunch cilantro\n" +
                        "1 bunch green onions (5 green onions)\n" +
                        "3 medium tomatoes\n" +
                        "1 avocado (optional)\n" +
                        "1 Tablespoon canola or vegetable oil\n" +
                        "2 Tablespoons vinegar or lime juice\n" +
                        "1⁄2 teaspoon each salt and pepper",
            
                "    Drain and rinse the black-eyed peas (or black beans) and corn.\n" +
                        "    Finely chop the cilantro and green onions.\n" +
                        "    Dice the tomatoes and avocado.\n" +
                        "    Combine all veggies in a large bowl.\n" +
                        "    Mix oil, vinegar or lime juice, salt and pepper together in a small bowl.\n" +
                        "    Pour oil mixture over salad ingredients and toss lightly.\n" +
                        "    Refrigerate leftovers within 2 hours.")
        );
        arcp.add(new RecipeItem("Apple Spice Baked Oatmeal",
                "e",
                r[2]/*Desserts*/,
                "1 egg, beaten\n" +
                        "1⁄2 cup applesauce\n" +
                        "1 1⁄2 cups nonfat or 1% milk\n" +
                        "1 teaspoon vanilla\n" +
                        "2 Tablespoons oil\n" +
                        "1 apple, chopped (about 1 ½ cups)\n" +
                        "2 cups old fashioned rolled oats\n" +
                        "1 teaspoon baking powder\n" +
                        "1⁄4 teaspoon salt\n" +
                        "1 teaspoon cinnamon\n" +
                        "\n" +
                        "TOPPING\n" +
                        "2 Tablespoons brown sugar\n" +
                        "2 Tablespoons chopped nuts (optional)",
            
                "    Preheat oven to 375 degrees. Lightly oil or spray an 8\" x 8\" baking dish.\n" +
                        "    Combine the egg, applesauce, milk, vanilla and oil in a bowl. Mix in the apple.\n" +
                        "    In a separate bowl, mix the rolled oats, baking powder, salt and cinnamon. Add to the liquid ingredients and mix well.\n" +
                        "    Pour mixture into baking dish, and bake for 25 minutes.\n" +
                        "    Remove from oven and sprinkle with brown sugar and (optional) nuts.\n" +
                        "    Return to oven and broil for 3 to 4 minutes until top is browned and the sugar bubbles.\n" +
                        "    Serve warm. Refrigerate leftovers within 2 hours.")
        );
        arcp.add(new RecipeItem("Banana Boats",
                "f",
                r[2]/*Desserts*/,
                "1 banana with peel\n" +
                        "1 Tablespoon crushed pineapple\n" +
                        "1 Tablespoon coconut flakes",
            
                "    Leave the peel on the banana and make a deep cut down the long side through the peel and into the banana. Do not cut all the way through.\n" +
                        "    Slightly pull the cut apart to make a pocket in the banana.\n" +
                        "    Fill the pocket with crushed pineapple and top with coconut flakes.\n" +
                        "    Wrap the banana in foil. Place on a grill or near the coals of a campfire\n" +
                        "    Heat until warm, about 5 minutes at medium high heat.\n" +
                        "    Carefully remove the foil packet from the heat. Let cool slightly and remove foil. The peel may be black but the inside will be warm and delicious.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    Not grilling?  Bake the foil packets in the oven at 350 degrees for about 15 minutes.\n" +
                        "    Try other fillings – peanut butter, chopped nuts, other fruit, a drizzle of honey or a sprinkle of cinnamon.\n" +
                        "    Honey is not recommended for children under 1 year old.")
        );
        arcp.add(new RecipeItem("Banana Bobs",
                "g",
                r[2]/*Desserts*/,
                "1 large banana cut into ½ inch slices\n" +
                        "1⁄4 cup low-fat vanilla yogurt\n" +
                        "2 Tablespoons oat and honey granola cereal",
            
                "    Divide the sliced banana pieces between two plates.\n" +
                        "    Place 2 tablespoons of yogurt onto each plate.\n" +
                        "    Place 1 tablespoon of granola cereal onto each plate.\n" +
                        "    Use fork to pick up a slice of banana and dip into the yogurt, then into the cereal.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    Try different flavors of yogurt.")
        );
        arcp.add(new RecipeItem("Creamy Potato Leek Soup",
                "h",
                r[3]/*Soup*/,
                "3 leeks (about 3 cups diced)\n" +
                        "3 potatoes (about 3 cups diced)\n" +
                        "2 Tablespoons butter or margarine\n" +
                        "4 1⁄2 cups chicken broth\n" +
                        "1⁄4 cup 1% milk\n" +
                        "2 garlic cloves, minced or 1/2 teaspoon garlic powder\n" +
                        "1⁄2 teaspoon black pepper",
            
                "    Remove root and green tops from leeks.  Slice in half lengthwise and rinse well under running water.  Slice crosswise into ¼ inch slices.\n" +
                        "    Scrub potatoes well; cut into small cubes.\n" +
                        "    Melt butter or margarine in a 2-quart saucepan over medium heat.\n" +
                        "    Add garlic and chopped leeks. Cook until softened.\n" +
                        "    Add potatoes and enough broth to cover.  Cover pan and simmer until potatoes are soft.  Mash with a potato masher or fork until potatoes are fairly smooth.\n" +
                        "    Add remaining broth, milk and pepper.  Simmer for about 5 minutes.\n" +
                        "    Refrigerate leftovers within 2 hours.")
        );
        arcp.add(new RecipeItem("Curried Pumpkin Soup",
                "i",
                r[3]/*Soup*/,
                "1⁄2 pound fresh mushrooms, sliced\n" +
                        "1⁄2 cup chopped onion\n" +
                        "2 Tablespoons margarine\n" +
                        "2 Tablespoons all-purpose flour\n" +
                        "1 teaspoon curry powder\n" +
                        "3 cups vegetable broth (see notes)\n" +
                        "1 can (15 ounces) solid-pack pumpkin\n" +
                        "1 can (12 ounces) evaporated milk\n" +
                        "1 Tablespoon honey\n" +
                        "1⁄2 teaspoon salt\n" +
                        "1⁄4 teaspoon pepper\n" +
                        "1⁄4 teaspoon ground nutmeg fresh or frozen chives (optional)",
            
                "    In 4 quart (or larger) saucepan, sauté the mushrooms and onion in margarine until tender. Stir in the flour and curry powder until blended. Gradually add the broth.\n" +
                        "    Bring to a boil; cook and stir for 2 minutes or until thickened.\n" +
                        "    Add the pumpkin, milk, honey, salt, pepper, and nutmeg; heat through.\n" +
                        "    Garnish with chives if desired.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    Broth can be canned or made using bouillon. For each cup of broth use 1 cup very hot water and 1 teaspoon or 1 cube bouillon.\n" +
                        "    Try substituting cooked winter squash or cooked pumpkin for canned pumpkin. Mash and measure out 2 cups for soup.\n" +
                        "    Honey is not recommended for children under 1 year old.")
        );
        arcp.add(new RecipeItem("Carrot Pancakes",
                "j",
                r[4]/*Pancakes*/,
                "1⁄2 cup oats (quick or old-fashioned)\n" +
                        "3⁄4 cup buttermilk\n" +
                        "1⁄2 cup carrots, finely grated\n" +
                        "1 egg\n" +
                        "1 Tablespoon oil\n" +
                        "1⁄4 cup nonfat or 1% milk\n" +
                        "1⁄2 teaspoon vanilla extract (optional)\n" +
                        "1⁄2 cup all purpose flour\n" +
                        "1 Tablespoon sugar\n" +
                        "1 teaspoon baking powder\n" +
                        "1⁄2 teaspoon baking soda\n" +
                        "1⁄2 teaspoon salt\n" +
                        "1⁄2 teaspoon cinnamon (optional)",
            
                "    Mix oatmeal and buttermilk and set aside to soak.\n" +
                        "    Peel and grate the carrot with a fine grater.\n" +
                        "    Add egg, oil, milk and vanilla extract, if desired, to the oatmeal mixture; beat well. Stir in the grated carrot.\n" +
                        "    Measure dry ingredients and stir into the wet ingredients until batter is fairly smooth. If it seems too thick add a small amount of milk.\n" +
                        "    Lightly spray a large skillet or griddle with non-stick cooking spray or lightly wipe with oil. Heat skillet or griddle over medium-high heat (350 degrees in an electric skillet). For each pancake, pour about 1/4 cup of batter onto the hot griddle.\n" +
                        "    Cook until pancakes are puffed and dry around edges. Turn and cook other side until golden brown.\n" +
                        "    Refrigerate leftovers within 2 hours.\n" +
                        "\n" +
                        "Notes\n" +
                        "\n" +
                        "    To see if skillet is hot enough, sprinkle with a few drops of water. If drops skitter around, heat is just right.\n" +
                        "    Top with applesauce, fresh fruit or yogurt.")
        );
        arcp.add(new RecipeItem("Favorite Pancakes (with Eggs)",
                "k",
                r[4]/*Pancakes*/,
                "1 1⁄4 cups all-purpose flour\n" +
                        "1 Tablespoon baking powder\n" +
                        "1 Tablespoon sugar\n" +
                        "1⁄4 teaspoon salt\n" +
                        "1 egg\n" +
                        "1 cup non-fat or 1% milk (add 2 Tablespoons for thinner pancakes)\n" +
                        "2 Tablespoons vegetable oil\n" +
                        "1 teaspoon vanilla (optional)",
            
                "    Mix together flour, baking powder, sugar and salt in a medium bowl.\n" +
                        "    In a separate bowl, beat egg until well blended. Add milk and oil.\n" +
                        "    Add liquids to dry ingredients and stir just until lumps disappear.\n" +
                        "    Lightly spray a large skillet or griddle with non-stick cooking spray or lightly wipe with oil. Heat skillet or griddle over medium-high heat (350 degrees in an electric skillet). Pour about ¼ cup of batter per pancake onto the hot skillet or griddle. Cook until bubbles come to the surface of the pancake and the edges start to look dry. Turn once. Bake until bottom is golden brown.")
        );
        
        return arcp;
    }
    
}
