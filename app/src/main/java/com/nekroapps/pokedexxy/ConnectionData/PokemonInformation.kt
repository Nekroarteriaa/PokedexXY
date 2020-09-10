package com.nekroapps.pokedexxy.ConnectionData

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PokemonInformation (var abilities: ArrayList<AbilityDescription>,
                               var base_experience:Int,
                               var forms: ArrayList<PokemonForm>,
                               var height:Int,
                               var held_items: ArrayList<PokemonHeldedItem>,
                               var id:Int,
                               var is_default: Boolean,
                               var location_area_encounters: String,
                               var moves: ArrayList<PokemonMoveList>,
                               var name:String,
                               var order:Int,
                               var species: PokemonSpecies,
                               var stats: ArrayList<PokemonBaseStats>,
                               var types: ArrayList<PokemonType>,
                               var weight: Int): Serializable


data class AbilityDescription(var ability: Ability, var is_hidden:Boolean, var slot:Int): Serializable
data class PokemonForm(var name: String, var url: String):Serializable
data class PokemonHeldedItem(var item: PokeItem):Serializable
data class PokemonMoveList(var move: PokeMove, var version_group_details: ArrayList<PokeMoveGenerationDetail>):Serializable
data class PokemonSpecies(var name: String, var url: String):Serializable
data class PokemonBaseStats(var base_stat:Int, var effort: Int, var stat:Stat):Serializable
data class PokemonType(var slot: Int, var type: PokemonTypeInfo):Serializable


data class  Ability(var name: String, var url: String):Serializable
data class PokeItem (var name: String, var url: String): Serializable
data class PokeMove(var name: String, var url: String):Serializable
data class PokeMoveGenerationDetail(var level_learn_at: Int,
                                    var move_learn_method: PokeMoveLearnMethod,
                                    var version_group:PokemonGameVersion):Serializable


data class PokeMoveLearnMethod(var name: String, var url: String):Serializable
data class PokemonGameVersion(var name: String, var url: String):Serializable
data class Stat(var name: String, var url: String):Serializable
data class PokemonTypeInfo(var name: String, var url: String):Serializable



data class DamageBehaviour(var double_damage_from: ArrayList<PokemonTypeInfo>,
                           var double_damage_to: ArrayList<PokemonTypeInfo>,
                           var half_damage_from: ArrayList<PokemonTypeInfo>,
                           var half_damage_to: ArrayList<PokemonTypeInfo>,
                           var no_damage_from: ArrayList<PokemonTypeInfo>,
                           var no_damage_to: ArrayList<PokemonTypeInfo>):Serializable


data class PokemonGeneration(var game_index: Int, var generation: PokemonGenerationInfo):Serializable
data class PokemonGenerationInfo(var name: String, var url: String):Serializable
data class PokemonThatSharesSameType(var pokemon:PokemonTypeInfo, var slot:Int):Serializable

data class PokeMoveDetail(var accuracy: Int,
                          var contest_combos:ContestCombos,
                          var contest_effect: ContestEffect,
                          var contest_type:ContestType,
                          var damage_class:Damage,
                          var effect_chance:Int,
                          var effect_changes: ArrayList<AbilityEffectChange>,
                          var effect_entries: ArrayList<EffectMove>,
                          var flavor_text_entries : ArrayList<Flavor>,
                          var generation: PokemonGenerationInfo,
                          var id: Int,
                          var machines: ArrayList<Machines>,
                          var meta: Metadata,
                          var name: String,
                          var names:ArrayList<InternationalName>,
                          var past_values : ArrayList<PastMoveStatsValues>,
                          var power: Int,
                          var pp: Int,
                          var priority : Int,
                          var stat_changes: ArrayList<StatsMoves>,
                          var super_contest_effect: ContestEffect,
                          var target: UseBefore,
                          var type: PokemonTypeInfo)


data class ContestCombos(var normal: ContestNormalCombo,@SerializedName("super") var super_combo: ContestSuperCombo):Serializable
data class ContestNormalCombo(var use_after: ArrayList<UseAfter>, var use_before: ArrayList<UseBefore>):Serializable
data class ContestSuperCombo(var use_after: ArrayList<UseAfter>, var use_before: ArrayList<UseBefore>):Serializable

data class UseAfter(var name: String, var url: String):Serializable
data class UseBefore(var name: String, var url: String):Serializable
data class ContestEffect(var url: String):Serializable
data class ContestType(var name: String, var url: String):Serializable
data class Damage(var name: String, var url: String):Serializable
data class AbilityEffectChange(var effect_entries: ArrayList<EffectMove>, var version_group: PokemonGameVersion):Serializable
data class EffectMove(var effect: String, var language: PokemonGameVersion, var short_effect:String):Serializable
data class Flavor(var flavor_text: String, var language: PokemonGameVersion, var version_group: PokemonGameVersion):Serializable
data class Machines(var machine: ContestEffect, var version_group: PokemonGameVersion):Serializable
data class Metadata(var ailment: PokemonGameVersion, var ailment_chance: Int, var category: PokemonGameVersion, var crit_rate:Int, var drain:Int, var flinch_chance: Int, var healing: Int,
                    var max_hits: Int, var max_turns: Int, var min_hits: Int, var min_turns:Int, var stat_chance: Int):Serializable

data class InternationalName(var language: PokemonGameVersion, var name: String):Serializable
data class PastMoveStatsValues(var accuracy: Int, var effect_chance: Int, var power: Int, var pp: Int, var effect_entries: ArrayList<EffectMove>, var type: PokemonTypeInfo, var version_group: PokemonGameVersion):Serializable
data class StatsMoves(var change:Int, var stat: Damage)
