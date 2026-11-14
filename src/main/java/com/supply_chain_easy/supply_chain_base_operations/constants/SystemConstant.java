package com.supply_chain_easy.supply_chain_base_operations.constants;

import java.util.UUID;

public class SystemConstant {
    public static int DEFAULT_PASSWORD_LENGTH=10;
    public final static String DEFAULT_PROFILE_PIC_URL="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN3KRqNa2mfm6UXxWGf-OyJwiz-Fi5ysWVR4iNke4TfA&s=10";
    public final static UUID COMPANY_REGISTRATION_TEMPLATE_ID = UUID.fromString("1acb58c5-fac9-4c52-8d6b-7fc8026a42f2");
    public final static long JWT_EXPIRATION_TIME = 900000L;
}
