package com.example.londondestination;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class FragmentGuwehArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private FragmentGuwehArgs() {
  }

  @SuppressWarnings("unchecked")
  private FragmentGuwehArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static FragmentGuwehArgs fromBundle(@NonNull Bundle bundle) {
    FragmentGuwehArgs __result = new FragmentGuwehArgs();
    bundle.setClassLoader(FragmentGuwehArgs.class.getClassLoader());
    if (bundle.containsKey("imageResId")) {
      int imageResId;
      imageResId = bundle.getInt("imageResId");
      __result.arguments.put("imageResId", imageResId);
    } else {
      throw new IllegalArgumentException("Required argument \"imageResId\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("nama")) {
      String nama;
      nama = bundle.getString("nama");
      if (nama == null) {
        throw new IllegalArgumentException("Argument \"nama\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("nama", nama);
    } else {
      throw new IllegalArgumentException("Required argument \"nama\" is missing and does not have an android:defaultValue");
    }
    if (bundle.containsKey("deskripsi")) {
      String deskripsi;
      deskripsi = bundle.getString("deskripsi");
      if (deskripsi == null) {
        throw new IllegalArgumentException("Argument \"deskripsi\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("deskripsi", deskripsi);
    } else {
      throw new IllegalArgumentException("Required argument \"deskripsi\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static FragmentGuwehArgs fromSavedStateHandle(@NonNull SavedStateHandle savedStateHandle) {
    FragmentGuwehArgs __result = new FragmentGuwehArgs();
    if (savedStateHandle.contains("imageResId")) {
      int imageResId;
      imageResId = savedStateHandle.get("imageResId");
      __result.arguments.put("imageResId", imageResId);
    } else {
      throw new IllegalArgumentException("Required argument \"imageResId\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("nama")) {
      String nama;
      nama = savedStateHandle.get("nama");
      if (nama == null) {
        throw new IllegalArgumentException("Argument \"nama\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("nama", nama);
    } else {
      throw new IllegalArgumentException("Required argument \"nama\" is missing and does not have an android:defaultValue");
    }
    if (savedStateHandle.contains("deskripsi")) {
      String deskripsi;
      deskripsi = savedStateHandle.get("deskripsi");
      if (deskripsi == null) {
        throw new IllegalArgumentException("Argument \"deskripsi\" is marked as non-null but was passed a null value.");
      }
      __result.arguments.put("deskripsi", deskripsi);
    } else {
      throw new IllegalArgumentException("Required argument \"deskripsi\" is missing and does not have an android:defaultValue");
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getImageResId() {
    return (int) arguments.get("imageResId");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getNama() {
    return (String) arguments.get("nama");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public String getDeskripsi() {
    return (String) arguments.get("deskripsi");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("imageResId")) {
      int imageResId = (int) arguments.get("imageResId");
      __result.putInt("imageResId", imageResId);
    }
    if (arguments.containsKey("nama")) {
      String nama = (String) arguments.get("nama");
      __result.putString("nama", nama);
    }
    if (arguments.containsKey("deskripsi")) {
      String deskripsi = (String) arguments.get("deskripsi");
      __result.putString("deskripsi", deskripsi);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public SavedStateHandle toSavedStateHandle() {
    SavedStateHandle __result = new SavedStateHandle();
    if (arguments.containsKey("imageResId")) {
      int imageResId = (int) arguments.get("imageResId");
      __result.set("imageResId", imageResId);
    }
    if (arguments.containsKey("nama")) {
      String nama = (String) arguments.get("nama");
      __result.set("nama", nama);
    }
    if (arguments.containsKey("deskripsi")) {
      String deskripsi = (String) arguments.get("deskripsi");
      __result.set("deskripsi", deskripsi);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    FragmentGuwehArgs that = (FragmentGuwehArgs) object;
    if (arguments.containsKey("imageResId") != that.arguments.containsKey("imageResId")) {
      return false;
    }
    if (getImageResId() != that.getImageResId()) {
      return false;
    }
    if (arguments.containsKey("nama") != that.arguments.containsKey("nama")) {
      return false;
    }
    if (getNama() != null ? !getNama().equals(that.getNama()) : that.getNama() != null) {
      return false;
    }
    if (arguments.containsKey("deskripsi") != that.arguments.containsKey("deskripsi")) {
      return false;
    }
    if (getDeskripsi() != null ? !getDeskripsi().equals(that.getDeskripsi()) : that.getDeskripsi() != null) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getImageResId();
    result = 31 * result + (getNama() != null ? getNama().hashCode() : 0);
    result = 31 * result + (getDeskripsi() != null ? getDeskripsi().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FragmentGuwehArgs{"
        + "imageResId=" + getImageResId()
        + ", nama=" + getNama()
        + ", deskripsi=" + getDeskripsi()
        + "}";
  }

  public static final class Builder {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    public Builder(@NonNull FragmentGuwehArgs original) {
      this.arguments.putAll(original.arguments);
    }

    @SuppressWarnings("unchecked")
    public Builder(int imageResId, @NonNull String nama, @NonNull String deskripsi) {
      this.arguments.put("imageResId", imageResId);
      if (nama == null) {
        throw new IllegalArgumentException("Argument \"nama\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("nama", nama);
      if (deskripsi == null) {
        throw new IllegalArgumentException("Argument \"deskripsi\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("deskripsi", deskripsi);
    }

    @NonNull
    public FragmentGuwehArgs build() {
      FragmentGuwehArgs result = new FragmentGuwehArgs(arguments);
      return result;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setImageResId(int imageResId) {
      this.arguments.put("imageResId", imageResId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setNama(@NonNull String nama) {
      if (nama == null) {
        throw new IllegalArgumentException("Argument \"nama\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("nama", nama);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public Builder setDeskripsi(@NonNull String deskripsi) {
      if (deskripsi == null) {
        throw new IllegalArgumentException("Argument \"deskripsi\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("deskripsi", deskripsi);
      return this;
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    public int getImageResId() {
      return (int) arguments.get("imageResId");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getNama() {
      return (String) arguments.get("nama");
    }

    @SuppressWarnings({"unchecked","GetterOnBuilder"})
    @NonNull
    public String getDeskripsi() {
      return (String) arguments.get("deskripsi");
    }
  }
}
