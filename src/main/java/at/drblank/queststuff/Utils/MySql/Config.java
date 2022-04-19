package at.drblank.queststuff.Utils.MySql;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Config {
  private File f;
  
  private YamlConfiguration c;
  
  public Config(String FilePath, String FileName) {
    this.f = new File(FilePath, FileName);
    this.c = YamlConfiguration.loadConfiguration(this.f);
  }
  
  public Config setValue(String ValuePath, Object Value) {
    this.c.set(ValuePath, Value);
    return this;
  }
  
  public boolean exist() {
    return this.f.exists();
  }
  
  public void delete() {
    this.f.delete();
  }
  
  public Object getObject(String ValuePath) {
    return this.c.get(ValuePath);
  }
  
  public int getInt(String ValuePath) {
    return this.c.getInt(ValuePath);
  }
  
  public String getString(String ValuePath) {
    return this.c.getString(ValuePath);
  }
  
  public boolean getBoolean(String ValuePath) {
    return this.c.getBoolean(ValuePath);
  }
  
  public long getLong(String ValuePath) {
    return this.c.getLong(ValuePath);
  }
  
  public List<String> getStringList(String ValuePath) {
    return this.c.getStringList(ValuePath);
  }
  
  public Set<String> getKeys(boolean deep) {
    return this.c.getKeys(deep);
  }
  
  public ConfigurationSection getConfiguratinSection(String Section) {
    return this.c.getConfigurationSection(Section);
  }
  
  public Config save() {
    try {
      this.c.save(this.f);
    } catch (IOException iOException) {}
    return this;
  }
  
  public double getDouble(String ValuePath) {
    return this.c.getDouble(ValuePath);
  }
  
  public Object get(String ValuePath) {
    return this.c.get(ValuePath);
  }
}
