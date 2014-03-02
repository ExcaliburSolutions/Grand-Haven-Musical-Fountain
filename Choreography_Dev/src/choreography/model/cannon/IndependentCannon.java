/**
 * 
 */
package choreography.model.cannon;


/**
 * @author madridf
 *
 */
public class IndependentCannon extends Cannon {
    
    private String module;

    public IndependentCannon(int level) {
        super(level);
    }

    public IndependentCannon(int level, String name) {
        super(level, name);
    }
    
    public IndependentCannon(int level, String name, String module) {
        super(level, name);
        this.module = module;
    }
    
    public String getModule() {
        return module;
    }

}
