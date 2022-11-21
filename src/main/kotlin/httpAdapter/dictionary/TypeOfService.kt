package httpAdapter.dictionary

enum class TypeOfService(val value: String) {
    WEB("web"),
    ANDROID("android"),
    BOT_FACTORY("bot_factory"),
    ADMIN_METRICS("admin_metrics"),
    ADAPTER_HTTP("adapter_http"),
    ADAPTER_DATABASE("adapter_database"),
    BUSINESS_LOGIC("business_logic"),
    DOCS_GEN("docs_gen");
}