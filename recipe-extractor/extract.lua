#!/usr/bin/env lua
json = require "json"

output = {}
recipes = {}
items = {}

data = {}
data["extend"] = function (data, t)
    for n, recipe in ipairs(t) do
        rec = {}
        rec["name"] = recipe["name"]
        components = recipe["ingredients"] or recipe["normal"]["ingredients"]

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

data_path = arg[1] or os.getenv("HOME") .. "/.steam/steam/steamapps/common/Factorio/data"

for i, f in ipairs(files) do
    dofile(data_path .. "/base/prototypes/recipe/" .. f .. ".lua")
end

data["extend"] = function (data, t)
    for n, item in ipairs(t) do
        it = {["name"] = item["name"],
              ["stacksize"] = item["stack_size"]}
        table.insert(items, it)
    end
end

files = {
    "ammo",
    "armor",
    "capsule",
    "demo-ammo",
    "demo-armor",
--    "demo-gun",
    "demo-item-groups",
    "demo-item",
    "demo-turret",
    "equipment",
--    "gun", FIXME: No support for guns (make peace not war)
    "item",
    "mining-tools",
    "module",
    "turret"}

for i, f in ipairs(files) do
    dofile(data_path .. "/base/prototypes/item/" .. f .. ".lua")
end

output["recipes"] = recipes
output["items"] = items
print(json.encode(output))
