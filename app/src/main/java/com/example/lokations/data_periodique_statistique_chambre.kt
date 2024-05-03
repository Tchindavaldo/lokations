package com.example.lokations

data class data_periodique_statistique_chambre(val periode: String,val date: String,
                                               val visite_sur_app: Int,val evolu_visite_sur_app: Int,
                                               val visite_sur_applm: Int,val evolu_visite_sur_applm: Int,
                                               val com_posi: Int,val evolu_com_posi: Int,
                                               val com_neg: Int,val evolu_com_neg: Int,
                                               val classement_app: Int,val evolu_classement_app: Int,
                                               val classement_rech: Int,val evolu_classement_rech: Int,
                                               val like_posi: Int,val like_neg: Int,
                                               val note: Int,val nbr_vote: Int)
