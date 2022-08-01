package cf.warriorcrystal.evo.module.modules.movement;

import cf.warriorcrystal.evo.Evo;
import cf.warriorcrystal.evo.module.Module;
import de.Hero.settings.Setting;

import java.util.ArrayList;

public class Step extends Module {

    Setting speed;
    Setting block;
    Setting toggleOnStep;
    Setting stepOn;

    public Step() {
        super("Step", Category.MOVEMENT);
        ArrayList<String> blocks = new ArrayList<>();
        blocks.add("2");
        blocks.add("3");
        blocks.add("4");
        Evo.getInstance().settingsManager.rSetting(block = new Setting("Height", this, "2", blocks));

        ArrayList<String> stepOns = new ArrayList<>();
        stepOns.add("Collide");
        stepOns.add("Jump");
        stepOns.add("Vanilla");
        Evo.getInstance().settingsManager.rSetting(stepOn = new Setting("StepOn", this, "Collide", stepOns));

        Evo.getInstance().settingsManager.rSetting(toggleOnStep = new Setting("ToggleStep", this, true));

    }


    @Override
    public void onUpdate() {

        if (stepOn.getValString().equalsIgnoreCase("collide") && mc.player.collidedVertically && mc.player.collidedHorizontally) {
            doVelocity();
        }
        if (stepOn.getValString().equalsIgnoreCase("jump") && mc.gameSettings.keyBindJump.isPressed()) {
            doVelocity();
        }
        if (stepOn.getValString().equals("Vanilla")){
            mc.player.stepHeight=mc.player.onGround?2.0f:0.6f;
        }
    }

    @Override
    public void onDisable(){
        mc.player.stepHeight=0.6f;
    }

    public void doVelocity() {
        if (block.getValString().equalsIgnoreCase("2")) {
            mc.player.setVelocity(.0, .56, .0);

            if (toggleOnStep.getValBoolean()) this.toggle();
        }
        if (block.getValString().equalsIgnoreCase("3")) {
            mc.player.setVelocity(.01, .7, .01);
            if (toggleOnStep.getValBoolean()) this.toggle();
        }
        if (block.getValString().equalsIgnoreCase("4")) {
            mc.player.setVelocity(.01, .84, .01);
            if (toggleOnStep.getValBoolean()) this.toggle();
        }

    }
}