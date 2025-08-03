CREATE TABLE `user_details` (
                         `id` SMALLINT PRIMARY KEY ,
                         `fullname` VARCHAR(255) NOT NULL,
                         `weight` decimal NOT NULL ,
                         `height` decimal NOT NULL ,
                         `city` VARCHAR(255) NOT NULL ,
                         `date` VARCHAR(255) NOT NULL
);

/*private Long id;
// Note! do not use "order" on field primary key because it'll access fails
    private String fullname;
    private Float weight;
    private Float height;
    private String city;
    @Setter(AccessLevel.PRIVATE)
    private String date;*/