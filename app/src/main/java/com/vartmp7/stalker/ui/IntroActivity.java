/*
 * MIT License
 *
 * Copyright (c) 2020 VarTmp7
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vartmp7.stalker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;
import com.vartmp7.stalker.MainActivity;
import com.vartmp7.stalker.R;
import com.vartmp7.stalker.ui.organizations.OrganizationsFragment;

import java.util.Arrays;
import java.util.List;

public class IntroActivity extends OnboarderActivity implements OnboarderPageChangeListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .title("Donut")
                        .description("Android 1.6")
                        .imageResourceId( R.drawable.ic_add_circle_outline_black_24dp)
                        .backgroundColorId(R.color.black)
                        .titleColorId(R.color.vartmp7bluChiaro)
                        .descriptionColorId(R.color.vartmp7bluChiaro)
                        .multilineDescriptionCentered(true)
                        .build(),

                // No need to write all of them :P

                new OnboarderPage.Builder()
                        .title("oreo")
                        .description("Android 1.6")
                        .imageResourceId( R.drawable.ic_add_circle_outline_black_24dp)
                        .backgroundColorId(R.color.black)
                        .titleColorId(R.color.vartmp7bluChiaro)
                        .descriptionColorId(R.color.vartmp7bluChiaro)
                        .multilineDescriptionCentered(true)
                        .build()
        );
        setOnboarderPageChangeListener(this);
        initOnboardingPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        // implement your logic, save induction has done to sharedPrefs
        Toast.makeText(this, "Finish button was pressed", Toast.LENGTH_SHORT).show();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(OrganizationsFragment.FIRST_LOG,false).apply();
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onPageChanged(int position) {
//        Toast.makeText(this, "onPageChanged: " + position, Toast.LENGTH_SHORT).show();
    }
}
