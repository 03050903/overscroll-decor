package me.everything.android.ui.overscroll.adapters;

import android.support.v4.view.ViewPager;
import android.view.View;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;

/**
 * Created by Bruce too
 * On 2016/6/16
 * At 14:51
 * An adapter to enable over-scrolling over object of {@link ViewPager}
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 * @see VerticalOverScrollBounceEffectDecorator
 */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {

    protected final ViewPager mViewPager;

    protected int mLastPagerPosition = 0;
    protected float mLastPagerScrollOffset;

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;

        mViewPager.addOnPageChangeListener(this);

        mLastPagerPosition = mViewPager.getCurrentItem();
        mLastPagerScrollOffset = 0f;
    }

    @Override
    public View getView() {
        return mViewPager;
    }

    @Override
    public boolean isInAbsoluteStart() {

        return mLastPagerPosition == 0 &&
                mLastPagerScrollOffset == 0f &&
                !mViewPager.canScrollHorizontally(-1);

        /**
         * 此处有个bug,当 page = 0 的时候往右滑动viewPager,
         * 此时出现overScroll效果,但是不抬起手指 向左滑动直到抵达屏幕
         * 的左边沿,再往右滑动 会发现viewPager在不该出现overScroll的
         * 时候出现了,并且viewPager的Page 0和1的发生了重叠
         *
         * 解决方法
         * 只有当mLastPagerPosition == 0 ..page = 0
         * mLastPagerScrollOffset == 0f ..滑动的距离=0
         * !mViewPager.canScrollHorizontally(-1) 可右滑动的判断 -1表示左
         */
//        PagerAdapter adapter = mViewPager.getAdapter();
//        if (null != adapter) {
//            if (mViewPager.getCurrentItem() == 0) {
//                return true;
//            }
//            return false;
//        }
//        return false;
    }

    @Override
    public boolean isInAbsoluteEnd() {

        return mLastPagerPosition == mViewPager.getAdapter().getCount()-1 &&
                mLastPagerScrollOffset == 0f &&
                !mViewPager.canScrollHorizontally(1);

//        PagerAdapter adapter = mViewPager.getAdapter();
//        if (null != adapter && adapter.getCount() > 0) {
//            if (mViewPager.getCurrentItem() == adapter.getCount() - 1) {
//                return true;
//            }
//            return false;
//        }
//        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mLastPagerPosition = position;
        mLastPagerScrollOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
