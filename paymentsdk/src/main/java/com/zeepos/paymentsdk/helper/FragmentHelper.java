package com.zeepos.paymentsdk.helper;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class FragmentHelper {
    private static FragmentHelper fragmentHelper = new FragmentHelper();
    private FragmentManager fragmentManager;

    private FragmentHelper() {
    }

    public static FragmentHelper getInstance() {
        if (fragmentHelper == null) {
            fragmentHelper = new FragmentHelper();
        }
        return fragmentHelper;
    }

    public void replaceFragment(int container, Fragment fragment, boolean addBackToStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        replaceFragment(container, fragment, addBackToStack, ft);
    }


    public void replaceFragment(int container, Fragment fragment, boolean addBackToStack, FragmentTransaction ft) {
        if (addBackToStack) {
            ft.addToBackStack(fragment.getTag());
        }
        //ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(container, fragment);
        ft.commitAllowingStateLoss();
    }

    public void addFragment(int container, Fragment fragment, boolean addBackToStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (addBackToStack) {
            ft.addToBackStack(fragment.getTag());
        }
        //ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(container, fragment);
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragmentByTag(String tag) {
        return getFragmentManager().findFragmentByTag(tag);
    }

    public void removeAllFragments() {

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) return;

        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

//        List<Fragment> fragments = getFragmentManager().getFragments();
//
//        if (!fragments.isEmpty()) {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//
//            for (Fragment fragment : fragments) {
//                if (fragment != null) {
//                    fragmentTransaction.remove(fragment);
//                }
//            }
//            fragmentTransaction.commitAllowingStateLoss();
//        }
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showDialog(DialogFragment fragment, Bundle extra, String tag) {
        showDialog(fragment,extra,tag,getFragmentManager().beginTransaction());
    }

    public void showDialog(DialogFragment fragment, Bundle extra, String tag, FragmentTransaction ft) {
        Fragment prev = getFragmentManager().findFragmentByTag(tag);
        if (extra != null) {
            fragment.setArguments(extra);
        }

        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(tag);
        fragment.show(ft, tag);
    }
}
