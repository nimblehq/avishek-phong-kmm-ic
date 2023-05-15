package co.nimblehq.avishek.phong.kmmic.data.local.datasource

import co.nimblehq.avishek.phong.kmmic.data.local.model.SurveyRealmObject
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

interface SurveyLocalDataSource {
    fun saveSurveys(surveys: List<SurveyRealmObject>)
    fun getSurveys(): List<SurveyRealmObject>
    fun removeAllSurveys()
}

class SurveyLocalDataSourceImpl(private val realm: Realm) : SurveyLocalDataSource {

    override fun saveSurveys(surveys: List<SurveyRealmObject>) {
        realm.writeBlocking {
            surveys.forEach {
                copyToRealm(it, UpdatePolicy.ALL)
            }
        }
    }

    override fun getSurveys(): List<SurveyRealmObject> {
        return realm.query<SurveyRealmObject>().find()
    }

    override fun removeAllSurveys() {
        realm.writeBlocking {
            val surveys = query<SurveyRealmObject>().find()
            delete(surveys)
        }
    }
}
