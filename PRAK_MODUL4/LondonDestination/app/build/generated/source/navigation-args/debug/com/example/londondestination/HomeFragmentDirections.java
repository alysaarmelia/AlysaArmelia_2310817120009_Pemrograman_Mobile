package com.example.londondestination;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToDetailFragment actionHomeFragmentToDetailFragment(
      int imageResId, @NonNull String nama, @NonNull String deskripsi) {
    return new ActionHomeFragmentToDetailFragment(imageResId, nama, deskripsi);
  }

  public static class ActionHomeFragmentToDetailFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToDetailFragment(int imageResId, @NonNull String nama,
        @NonNull String deskripsi) {
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
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToDetailFragment setImageResId(int imageResId) {
      this.arguments.put("imageResId", imageResId);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToDetailFragment setNama(@NonNull String nama) {
      if (nama == null) {
        throw new IllegalArgumentException("Argument \"nama\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("nama", nama);
      return this;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToDetailFragment setDeskripsi(@NonNull String deskripsi) {
      if (deskripsi == null) {
        throw new IllegalArgumentException("Argument \"deskripsi\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("deskripsi", deskripsi);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
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

    @Override
    public int getActionId() {
      return R.id.action_HomeFragment_to_detailFragment;
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

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToDetailFragment that = (ActionHomeFragmentToDetailFragment) object;
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
      if (getActionId() != that.getActionId()) {
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
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToDetailFragment(actionId=" + getActionId() + "){"
          + "imageResId=" + getImageResId()
          + ", nama=" + getNama()
          + ", deskripsi=" + getDeskripsi()
          + "}";
    }
  }
}
