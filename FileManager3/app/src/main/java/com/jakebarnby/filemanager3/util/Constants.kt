package com.jakebarnby.filemanager3.util

/**
 * Created by Jake on 6/1/2017.
 */

object Constants {

    object Sources {
        val LOCAL = "Local"
        val DROPBOX = "Dropbox"
        val GOOGLE_DRIVE = "Google Drive"
        val GOOGLE_DRIVE_FOLDER_MIME = "application/vnd.google-apps.folder"
        val ONEDRIVE = "OneDrive"
        val ONEDRIVE_INVALID_CHARS = "[\\/:*?\"<>|#% ]"
        val DROPBOX_CLIENT_ID = "rse09cxjnnn2yc1"
        val MAX_FILENAME_LENGTH = 255
    }

    object RequestCodes {
        val STORAGE_PERMISSIONS = 100
        val GOOGLE_SIGN_IN = 103
        val GOOGLE_PLAY_SERVICES = 105
        val ACCOUNTS_PERMISSIONS = 106
        val ACCOUNT_PICKER = 107
    }

    object Prefs {
        val PREFS = "filemanager-prefs"
        val DROPBOX_TOKEN_KEY = "dropbox-access-token"
        val GOOGLE_TOKEN_KEY = "google drive-access-token"
        val GOOGLE_NAME_KEY = "google drive-name"
        val ONEDRIVE_TOKEN_KEY = "onedrive-access-token"
        val ONEDRIVE_NAME_KEY = "onedrive-name"
        val TUT_SEEN_KEY = "seen-tutorial"
        val VIEW_TYPE_KEY = "view-type"
        val SORT_TYPE_KEY = "sort-type"
        val ORDER_TYPE_KEY = "order-type"
        val HIDDEN_FOLDER_KEY = "hidden-folder"
        val FOLDER_FIRST_KEY = "folders-first"
        val HIDE_ADS_KEY = "premium-enabled"
        val OPERATION_COUNT_KEY = "operation-count"
    }

    object Animation {
        val PROGRESS_DURATION = 1200

    }

    object RemoteConfig {
        val RC_CACHE_EXPIRATION_SECONDS = 60

        val TUT_PAGE_COUNT_KEY = "tut_page_count"

        val TUT_PAGE1_TITLE_KEY = "tut_page1_title"
        val TUT_PAGE1_CONTENT_KEY = "tut_page1_content"
        val TUT_PAGE1_SUMMARY_KEY = "tut_page1_summary"
        val TUT_PAGE1_IMAGE_KEY = "tut_page1_image"
        val TUT_PAGE1_BGCOLOR_KEY = "tut_page1_bgcolor"

        val TUT_PAGE2_TITLE_KEY = "tut_page2_title"
        val TUT_PAGE2_CONTENT_KEY = "tut_page2_content"
        val TUT_PAGE2_SUMMARY_KEY = "tut_page2_summary"
        val TUT_PAGE2_IMAGE_KEY = "tut_page2_image"
        val TUT_PAGE2_BGCOLOR_KEY = "tut_page2_bgcolor"

        val TUT_PAGE3_TITLE_KEY = "tut_page3_title"
        val TUT_PAGE3_CONTENT_KEY = "tut_page3_content"
        val TUT_PAGE3_SUMMARY_KEY = "tut_page3_summary"
        val TUT_PAGE3_IMAGE_KEY = "tut_page3_image"
        val TUT_PAGE3_BGCOLOR_KEY = "tut_page3_bgcolor"
    }

    object Analytics {
        val NO_DESTINATION = "NO DESTINATION"

        val PARAM_ERROR_VALUE = "error_value"
        val PARAM_SOURCE_NAME = "source_name"
        val PARAM_PURCHASE_PRICE = "purchase_price"
        val PARAM_PURCHASE_SKU = "purchase_sku"

        val EVENT_ERROR_DELETE = "error_deleting"
        val EVENT_ERROR_CACHE_CLEAR = "error_clearing_cache"
        val EVENT_ERROR_CREATE_FOLDER = "error_creating_folder"
        val EVENT_ERROR_RENAMING = "error_renaming"
        val EVENT_ERROR_OPENING_FILE = "error_opening_file"
        val EVENT_ERROR_COPYING = "error_copying_file"
        val EVENT_ERROR_DROPBOX_LOGOUT = "error_dropbox_logout"
        val EVENT_ERROR_PURCHASE = "error_purchase"

        val EVENT_SUCCESS_CREATE_FOLDER = "success_creating_folder"
        val EVENT_SUCCESS_RENAMING = "success_renaming"
        val EVENT_SUCCESS_OPEN_FILE = "success_opening_file"
        val EVENT_SUCCESS_COPYING = "success_copying"
        val EVENT_SUCCESS_DELETING = "success_deleting"
        val EVENT_SUCCESS_DROPBOX_DOWNLOAD = "success_dropbox_download"
        val EVENT_SUCCESS_GOOGLEDRIVE_DOWNLOAD = "success_googledrive_download"
        val EVENT_SUCCESS_ONEDRIVE_DOWNLOAD = "success_onedrive_download"
        val EVENT_SUCCESS_DROPBOX_UPLOAD = "success_dropbox_upload"
        val EVENT_SUCCESS_GOOGLEDRIVE_UPLOAD = "success_googledrive_upload"
        val EVENT_SUCCESS_ONEDRIVE_UPLOAD = "success_onedrive_upload"
        val EVENT_SUCCESS_PURCHASE = "success_purchase"

        val EVENT_LOGIN_DROPBOX = "login_dropbox"
        val EVENT_LOGIN_GOOGLE_DRIVE = "login_googledrive"
        val EVENT_LOGIN_ONEDRIVE = "login_onedrive"

        val EVENT_LOGOUT_DROPBOX = "logout_dropbox"
        val EVENT_LOGOUT_GOOGLEDRIVE = "logout_googledrive"
        val EVENT_LOGOUT_ONEDRIVE = "logout_onedrive"
        val EVENT_CANCELLED_PURCHASE = "cancelled_purchase"
        val EVENT_ERROR_ZIPPING = "error_zipping"
    }

    object ViewTypes {
        val LIST = 0
        val DETAILED_LIST = 1
        val GRID = 2
    }

    object SortTypes {
        val NAME = 0
        val TYPE = 1
        val SIZE = 2
        val MODIFIED_TIME = 3
    }

    object OrderTypes {
        val ASCENDING = 0
        val DESCENDING = 1
    }

    object DialogTags {
        val SORT_BY = "Sort by"
        val CREATE_FOLDER = "Create folder"
        val SETTINGS = "Settings"
    }

    object Billing {
        val SKU_PREMIUM = "premium"
    }

    object Ads {
        val ADMOB_ID = "ca-app-pub-6044629197845708~9185423482"
        val INTERSTITIAL_ID = "ca-app-pub-6044629197845708/9815268432"
        val SHOW_AD_COUNT = 6
    }

    val DIALOG_TITLE_KEY = "DIALOG_TITLE"
    val FILE_PATH_KEY = "FILE_PATH"
    val FRAGMENT_TITLE = "TITLE"
    val BYTES_TO_GIGABYTE = 1073741824.0
    val BYTES_TO_MEGABYTE = 1048576.0
    val LOCAL_ROOT = "ROOT_PATH"
    val ALL = "All"
    val DATE_TIME_FORMAT = "%02d:%02d %02d/%02d/%02d"
    val GRID_SIZE = 4
    val ADS_MENU_POSITION = 9
    val ADS_MENU_ID = 666666

    val MIME_LIST = listOf("application/eps",
        "application/illustrator",
        "application/msword",
        "application/pdf",
        "application/rtf",
        "application/vnd.apple.keynote",
        "application/vnd.apple.numbers",
        "application/vnd.apple.pages",
        "application/vnd.ms-excel",
        "application/vnd.ms-excel.addin.macroenabled.12",
        "application/vnd.ms-excel.sheet.binary.macroenabled.12",
        "application/vnd.ms-excel.sheet.macroenabled.12",
        "application/vnd.ms-excel.template.macroenabled.12",
        "application/vnd.ms-outlook",
        "application/vnd.ms-powerpoint",
        "application/vnd.ms-powerpoint.addin.macroenabled.12",
        "application/vnd.ms-powerpoint.presentation.macroenabled.12",
        "application/vnd.ms-powerpoint.slide.macroenabled.12",
        "application/vnd.ms-powerpoint.slideshow.macroenabled.12",
        "application/vnd.ms-powerpoint.template.macroenabled.12",
        "application/vnd.ms-word.document.macroenabled.12",
        "application/vnd.ms-word.template.macroenabled.12",
        "application/vnd.oasis.opendocument.presentation",
        "application/vnd.oasis.opendocument.presentation-template",
        "application/vnd.oasis.opendocument.spreadsheet",
        "application/vnd.oasis.opendocument.spreadsheet-template",
        "application/vnd.oasis.opendocument.text",
        "application/vnd.oasis.opendocument.text-template",
        "application/vnd.openxmlformats-officedocument.presentationml.presentation",
        "application/vnd.openxmlformats-officedocument.presentationml.slide",
        "application/vnd.openxmlformats-officedocument.presentationml.slideshow",
        "application/vnd.openxmlformats-officedocument.presentationml.template",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.template",
        "application/vnd.sun.xml.calc",
        "application/vnd.sun.xml.calc.template",
        "application/vnd.sun.xml.impress",
        "application/vnd.sun.xml.impress.template",
        "application/vnd.sun.xml.writer",
        "application/vnd.sun.xml.writer.template",
        "application/vnd.visio",
        "application/wordperfect",
        "application/x-cpio",
        "application/x-tar",
        "application/zip",
        "image/bmp",
        "image/cgm",
        "image/gif",
        "image/ief",
        "image/jp2",
        "image/jpeg",
        "image/png",
        "image/tiff",
        "image/vnd.adobe.photoshop",
        "image/vnd.adobe.premiere",
        "image/x-cmu-raster",
        "image/x-dwt",
        "image/x-portable-anymap",
        "image/x-portable-bitmap",
        "image/x-portable-graymap",
        "image/x-portable-pixmap",
        "image/x-raw-adobe",
        "image/x-raw-canon",
        "image/x-raw-fuji",
        "image/x-raw-hasselblad",
        "image/x-raw-kodak",
        "image/x-raw-leica",
        "image/x-raw-minolta",
        "image/x-raw-nikon",
        "image/x-raw-olympus",
        "image/x-raw-panasonic",
        "image/x-raw-pentax",
        "image/x-raw-red",
        "image/x-raw-sigma",
        "image/x-raw-sony",
        "image/x-xbitmap",
        "image/x-xpixmap",
        "image/x-xwindowdump",
        "text/csv",
        "text/html",
        "text/plain",
        "text/xml")
}
