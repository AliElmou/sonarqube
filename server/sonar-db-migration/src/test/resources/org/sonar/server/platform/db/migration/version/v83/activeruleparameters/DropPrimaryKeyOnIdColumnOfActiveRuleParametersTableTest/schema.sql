CREATE TABLE "ACTIVE_RULE_PARAMETERS"(
    "ID" INTEGER NOT NULL AUTO_INCREMENT (1,1),
    "UUID" VARCHAR(40) NOT NULL,
    "ACTIVE_RULE_ID" INTEGER NOT NULL,
    "RULES_PARAMETER_ID" INTEGER NOT NULL,
    "VALUE" VARCHAR(4000),
    "RULES_PARAMETER_KEY" VARCHAR(128)
);
ALTER TABLE "ACTIVE_RULE_PARAMETERS" ADD CONSTRAINT "PK_ACTIVE_RULE_PARAMETERS" PRIMARY KEY("ID");
CREATE INDEX "IX_ARP_ON_ACTIVE_RULE_ID" ON "ACTIVE_RULE_PARAMETERS"("ACTIVE_RULE_ID");
