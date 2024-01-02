package com.candra.dicodingreviewersubmissionjetpackcompose

import androidx.navigation.NavController
import org.junit.Assert

fun NavController.assertCurrentDestination(route: String) {
    Assert.assertEquals(route, currentBackStackEntry?.destination?.route)
}