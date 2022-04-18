def call2() {
  
  def DEV_USER = 'core'
  def DEV_IP = '35.202.30.232'
  def query=""
  node {
      parameters{
          string(name: 'Enter_Query',defaultValue: '', description: '')
      }
      stage('run sql query') {
          query=params.Enter_Query
          if (query.contains('"')){
              query=query.replaceAll('"','\\\\"')
          }
          sh """ssh ${DEV_USER}@${DEV_IP} 'kubectl exec -i data-mysql-0 -n core -- mysql -uroot -ptest@789 ${params.DB_Name} -e "${query}\\G"'"""
      }
  }
}
