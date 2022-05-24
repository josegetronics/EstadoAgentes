package sns.config;

public class ConfigBean implements java.io.Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void setUmbralCacheo(long umbralCacheo) {
    this.umbralCacheo = umbralCacheo;
  }

  public long getUmbralCacheo() {
    return umbralCacheo;
  }

  public ConfigBean() {
  }

  private long umbralCacheo;

}
