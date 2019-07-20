package project.template;


//basic implementation of a Skin

//imports 
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

//class definition 
class mainControlSkin extends SkinBase<mainControl> implements Skin<mainControl> {
	public mainControlSkin(mainControl cc) {
		//call the super class constructor
		super(cc);
	}
}