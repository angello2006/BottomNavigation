package com.example.bottomnavigasi

import android.view.SurfaceControl.Transaction

sealed class Destination(val route: String, val icon: Int, val title: String) {
    object Transactions : Destination(
        route = "transactions",
        icon = R.drawable.baseline_account_balance_wallet_24,
        title = "transactions"
    )
    object Budget : Destination(
        route = "budget",
        icon = R.drawable.baseline_attach_money_24,
        title = "Budget"
    )
    object Tasks : Destination(
        route = "tasks",
        icon = R.drawable.baseline_add_task_24,
        title = "Tasks"
    )
    object Settngs : Destination(
        route = "settings",
        icon = R.drawable.baseline_app_settings_alt_24,
        title = "settings"
    )
    companion object{
        val tolist = listOf(Transactions, Budget, Tasks, Settngs)
    }
}