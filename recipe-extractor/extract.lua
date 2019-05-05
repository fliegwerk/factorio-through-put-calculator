#!/usr/bin/env lua
json = require "json"

output = {}
recipes = {}

data = {}
data["extend"] = function (data, t)
    for n, recipe in ipairs(t) do
        rec = {}
        rec["name"] = recipe["name"]
        if recipe["normal"] == nil then
            components = recipe["ingredients"]
        else
            components = recipe["normal"]["ingredients"]
        end
        rec["craftingtime"] = recipe["energy_required"] or 0.5
        ingredients = {}
        for i, component in ipairs(components) do
            ing = {}
            ing["ingredient"] = component[1] or component["name"]
            ing["count"] = component[2] or component["amount"]
            table.insert(ingredients, ing)
        end
        rec["ingredients"] = ingredients

        rec["results"] = {["result"]=recipe["name"],
                          ["count"] =recipe["result_count"] or 1}
        table.insert(recipes, rec)
    end
end


files = {
    "ammo",
    "capsule",
    "demo-furnace-recipe",
    "demo-recipe",
    "demo-turret",
    "equipment",
    "fluid-recipe",
    "furnace-recipe",
    "inserter",
    "module",
    "recipe",
    "turret",
}

steampath = arg[1] or os.getenv("HOME") .. "/.steam/steam/steamapps/common/Factorio"

for i, f in ipairs(files) do
    dofile(steampath .. "/data/base/prototypes/recipe/" .. f .. ".lua")
end

output["recipes"] = recipes
print(json.encode(output))
