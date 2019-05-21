package de.fliegwerk.factorio.throughputcalc;

import de.fliegwerk.factorio.throughputcalc.machines.CraftingMachinePrototype;
import de.fliegwerk.factorio.throughputcalc.machines.EffectMachinePrototype;
import de.fliegwerk.factorio.throughputcalc.machines.MachineConstructor;
import de.fliegwerk.factorio.throughputcalc.modules.EffectType;
import de.fliegwerk.factorio.throughputcalc.modules.Module;
import de.fliegwerk.factorio.throughputcalc.recipes.Recipe;

import java.util.EnumMap;
import java.util.HashMap;

public class Program {
    public static void main(String[] args) {
        EffectMachinePrototype effectMachinePrototype = new EffectMachinePrototype("beacon", 2, 0.5);
        CraftingMachinePrototype craftingMachinePrototype = new CraftingMachinePrototype("assembling-machine-3", 4, 1.5);

        MachineConstructor<EffectMachinePrototype> effectMachine = new MachineConstructor<>(effectMachinePrototype);
        MachineConstructor<CraftingMachinePrototype> craftingMachine = new MachineConstructor<>(craftingMachinePrototype);


        EnumMap<EffectType, Double> effects1 = new EnumMap<>(EffectType.class);
        effects1.put(EffectType.speedBonus, 0.7);
        effects1.put(EffectType.energyConsumptionBonus, 0.5);
        EnumMap<EffectType, Double> effects2 = new EnumMap<>(EffectType.class);
        effects2.put(EffectType.energyConsumptionBonus, 0.3);
        Module module1 = new Module("module-1", effects1);
        Module module2 = new Module("module-2", effects2);

        effectMachine.addModule(module1);

        craftingMachine.addModule(module1);
        craftingMachine.addModule(module2);


        for (Module current : effectMachine.getModules()) {
            System.out.println(current);
        }

        for (Module current : craftingMachine.getModules()) {
            System.out.println(current);
        }

        Recipe recipe = new Recipe(new HashMap<>(), new HashMap<>(), 0.1, true);

        System.out.println(effectMachinePrototype);
        System.out.println(effectMachine);
        System.out.println(craftingMachinePrototype);
        System.out.println(craftingMachine);
    }
}
