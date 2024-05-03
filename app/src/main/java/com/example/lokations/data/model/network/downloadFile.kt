package com.example.lokations.data.model.network

class downloadFile {
/*
    fun download(context: Context){
    val fetchConfiguration = FetchConfiguration.Builder(context.appliction)
        .setDownloadConcurrentLimit(3)
        .build()
    val fetch = Fetch.Impl.getInstance(fetchConfiguration)

    val url = "http://192.168.100.175:80/lokation/mp.jpg"
    val file = "/path/to/save/file.txt"
    val request = Request(url, file)
    request.priority = Priority.HIGH
    request.networkType = NetworkType.ALL

    fetch.enqueue(request,
    { updatedRequest -> /* Request was successfully enqueued */ },
    { error -> /* An error occurred enqueuing the request */ })

}

















            val fileName = "mp.jpg"
            val file = File(getFilesDir(), fileName)
            val fileUri = Uri.fromFile(file)


            val url = "http://192.168.100.175:80/lokation/mp.jpg"
            val request = DownloadManager.Request(Uri.parse(url))
            request.setTitle("Mon fichier")
            request.setDescription("Téléchargement en cours...")
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationUri(fileUri)

            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadId = downloadManager.enqueue(request)

            val receiver = object : BroadcastReceiver() {
                @SuppressLint("Range")
                override fun onReceive(context: Context?, intent: Intent?) {
                    val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (id == downloadId) {
                        val query = DownloadManager.Query()
                        query.setFilterById(downloadId)
                        val cursor = downloadManager.query(query)
                        if (cursor.moveToFirst()) {
                            val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                                Toast.makeText(applicationContext, "Téléchargement réussi.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(applicationContext, "Téléchargement échoué.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


*/
}