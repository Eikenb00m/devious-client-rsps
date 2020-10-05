import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ht")
public class Huffman {
	@ObfuscatedName("o")
	static Thread ArchiveDiskActionHandler_thread;
	@ObfuscatedName("du")
	@ObfuscatedSignature(
		descriptor = "Liw;"
	)
	static Archive archive8;
	@ObfuscatedName("kk")
	@ObfuscatedGetter(
		intValue = 360288727
	)
	static int Client_plane;
	@ObfuscatedName("z")
	int[] masks;
	@ObfuscatedName("k")
	byte[] bits;
	@ObfuscatedName("s")
	int[] keys;

	public Huffman(byte[] var1) {
		int var2 = var1.length; // L: 9
		this.masks = new int[var2]; // L: 10
		this.bits = var1; // L: 11
		int[] var3 = new int[33]; // L: 12
		this.keys = new int[8]; // L: 13
		int var4 = 0; // L: 14

		for (int var5 = 0; var5 < var2; ++var5) { // L: 15
			byte var6 = var1[var5]; // L: 16
			if (var6 != 0) {
				int var7 = 1 << 32 - var6;
				int var8 = var3[var6];
				this.masks[var5] = var8;
				int var9;
				int var10;
				int var11;
				int var12;
				if ((var8 & var7) != 0) { // L: 22
					var9 = var3[var6 - 1];
				} else {
					var9 = var8 | var7; // L: 24

					for (var10 = var6 - 1; var10 >= 1; --var10) { // L: 25
						var11 = var3[var10]; // L: 26
						if (var11 != var8) { // L: 27
							break;
						}

						var12 = 1 << 32 - var10; // L: 28
						if ((var11 & var12) != 0) { // L: 29
							var3[var10] = var3[var10 - 1]; // L: 30
							break;
						}

						var3[var10] = var11 | var12;
					}
				}

				var3[var6] = var9;

				for (var10 = var6 + 1; var10 <= 32; ++var10) {
					if (var8 == var3[var10]) {
						var3[var10] = var9;
					}
				}

				var10 = 0; // L: 42

				for (var11 = 0; var11 < var6; ++var11) { // L: 43
					var12 = Integer.MIN_VALUE >>> var11; // L: 44
					if ((var8 & var12) != 0) { // L: 45
						if (this.keys[var10] == 0) { // L: 46
							this.keys[var10] = var4;
						}

						var10 = this.keys[var10]; // L: 47
					} else {
						++var10; // L: 49
					}

					if (var10 >= this.keys.length) { // L: 50
						int[] var13 = new int[this.keys.length * 2]; // L: 51

						for (int var14 = 0; var14 < this.keys.length; ++var14) { // L: 52
							var13[var14] = this.keys[var14];
						}

						this.keys = var13; // L: 53
					}

					var12 >>>= 1; // L: 55
				}

				this.keys[var10] = ~var5; // L: 57
				if (var10 >= var4) { // L: 58
					var4 = var10 + 1;
				}
			}
		}

	} // L: 60

	@ObfuscatedName("z")
	@ObfuscatedSignature(
		descriptor = "([BII[BII)I",
		garbageValue = "-141428387"
	)
	int compress(byte[] var1, int var2, int var3, byte[] var4, int var5) {
		int var6 = 0; // L: 63
		int var7 = var5 << 3; // L: 64

		for (var3 += var2; var2 < var3; ++var2) { // L: 65 66
			int var8 = var1[var2] & 255; // L: 67
			int var9 = this.masks[var8]; // L: 68
			byte var10 = this.bits[var8]; // L: 69
			if (var10 == 0) { // L: 70
				throw new RuntimeException("" + var8);
			}

			int var11 = var7 >> 3; // L: 71
			int var12 = var7 & 7; // L: 72
			var6 &= -var12 >> 31; // L: 73
			int var13 = (var12 + var10 - 1 >> 3) + var11; // L: 74
			var12 += 24; // L: 76
			var4[var11] = (byte)(var6 |= var9 >>> var12); // L: 77
			if (var11 < var13) { // L: 78
				++var11; // L: 79
				var12 -= 8; // L: 80
				var4[var11] = (byte)(var6 = var9 >>> var12); // L: 81
				if (var11 < var13) { // L: 82
					++var11; // L: 83
					var12 -= 8; // L: 84
					var4[var11] = (byte)(var6 = var9 >>> var12); // L: 85
					if (var11 < var13) { // L: 86
						++var11; // L: 87
						var12 -= 8; // L: 88
						var4[var11] = (byte)(var6 = var9 >>> var12); // L: 89
						if (var11 < var13) { // L: 90
							++var11; // L: 91
							var12 -= 8; // L: 92
							var4[var11] = (byte)(var6 = var9 << -var12); // L: 93
						}
					}
				}
			}

			var7 += var10; // L: 95
		}

		return (var7 + 7 >> 3) - var5; // L: 97
	}

	@ObfuscatedName("k")
	@ObfuscatedSignature(
		descriptor = "([BI[BIII)I",
		garbageValue = "-238004531"
	)
	int decompress(byte[] var1, int var2, byte[] var3, int var4, int var5) {
		if (var5 == 0) { // L: 101
			return 0;
		} else {
			int var6 = 0; // L: 102
			var5 += var4; // L: 103
			int var7 = var2;

			while (true) {
				byte var8 = var1[var7]; // L: 106
				if (var8 < 0) { // L: 108
					var6 = this.keys[var6];
				} else {
					++var6; // L: 109
				}

				int var9;
				if ((var9 = this.keys[var6]) < 0) { // L: 110
					var3[var4++] = (byte)(~var9); // L: 111
					if (var4 >= var5) { // L: 112
						break;
					}

					var6 = 0; // L: 113
				}

				if ((var8 & 64) != 0) { // L: 115
					var6 = this.keys[var6];
				} else {
					++var6; // L: 116
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 117
					var3[var4++] = (byte)(~var9); // L: 118
					if (var4 >= var5) { // L: 119
						break;
					}

					var6 = 0; // L: 120
				}

				if ((var8 & 32) != 0) { // L: 122
					var6 = this.keys[var6];
				} else {
					++var6; // L: 123
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 124
					var3[var4++] = (byte)(~var9); // L: 125
					if (var4 >= var5) { // L: 126
						break;
					}

					var6 = 0; // L: 127
				}

				if ((var8 & 16) != 0) { // L: 129
					var6 = this.keys[var6];
				} else {
					++var6; // L: 130
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 131
					var3[var4++] = (byte)(~var9); // L: 132
					if (var4 >= var5) { // L: 133
						break;
					}

					var6 = 0; // L: 134
				}

				if ((var8 & 8) != 0) { // L: 136
					var6 = this.keys[var6];
				} else {
					++var6; // L: 137
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 138
					var3[var4++] = (byte)(~var9); // L: 139
					if (var4 >= var5) { // L: 140
						break;
					}

					var6 = 0; // L: 141
				}

				if ((var8 & 4) != 0) { // L: 143
					var6 = this.keys[var6];
				} else {
					++var6; // L: 144
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 145
					var3[var4++] = (byte)(~var9); // L: 146
					if (var4 >= var5) { // L: 147
						break;
					}

					var6 = 0; // L: 148
				}

				if ((var8 & 2) != 0) { // L: 150
					var6 = this.keys[var6];
				} else {
					++var6; // L: 151
				}

				if ((var9 = this.keys[var6]) < 0) { // L: 152
					var3[var4++] = (byte)(~var9); // L: 153
					if (var4 >= var5) {
						break;
					}

					var6 = 0;
				}

				if ((var8 & 1) != 0) {
					var6 = this.keys[var6];
				} else {
					++var6;
				}

				if ((var9 = this.keys[var6]) < 0) {
					var3[var4++] = (byte)(~var9);
					if (var4 >= var5) {
						break;
					}

					var6 = 0;
				}

				++var7; // L: 105
			}

			return var7 + 1 - var2; // L: 165
		}
	}

	@ObfuscatedName("k")
	@ObfuscatedSignature(
		descriptor = "(III)V",
		garbageValue = "-1277930744"
	)
	public static void method4047(int var0, int var1) {
		VarbitDefinition var2 = TaskHandler.method3567(var0); // L: 33
		int var3 = var2.baseVar; // L: 34
		int var4 = var2.startBit; // L: 35
		int var5 = var2.endBit; // L: 36
		int var6 = Varps.Varps_masks[var5 - var4]; // L: 37
		if (var1 < 0 || var1 > var6) { // L: 38
			var1 = 0;
		}

		var6 <<= var4; // L: 39
		Varps.Varps_main[var3] = Varps.Varps_main[var3] & ~var6 | var1 << var4 & var6; // L: 40
	} // L: 41

	@ObfuscatedName("k")
	@ObfuscatedSignature(
		descriptor = "(IIIB)I",
		garbageValue = "81"
	)
	public static int method4041(int var0, int var1, int var2) {
		var2 &= 3; // L: 17
		if (var2 == 0) { // L: 18
			return var1;
		} else if (var2 == 1) { // L: 19
			return 7 - var0;
		} else {
			return var2 == 2 ? 7 - var1 : var0; // L: 20
		}
	}

	@ObfuscatedName("d")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;B)Ljava/lang/String;",
		garbageValue = "2"
	)
	public static String method4049(String var0) {
		int var1 = var0.length(); // L: 154
		char[] var2 = new char[var1]; // L: 155
		byte var3 = 2; // L: 156

		for (int var4 = 0; var4 < var1; ++var4) { // L: 157
			char var5 = var0.charAt(var4); // L: 158
			if (var3 == 0) { // L: 159
				var5 = Character.toLowerCase(var5);
			} else if (var3 == 2 || Character.isUpperCase(var5)) { // L: 160
				var5 = UserList.method5199(var5);
			}

			if (Character.isLetter(var5)) { // L: 161
				var3 = 0;
			} else if (var5 != '.' && var5 != '?' && var5 != '!') { // L: 162
				if (Character.isSpaceChar(var5)) { // L: 163
					if (var3 != 2) { // L: 164
						var3 = 1;
					}
				} else {
					var3 = 1; // L: 166
				}
			} else {
				var3 = 2;
			}

			var2[var4] = var5; // L: 167
		}

		return new String(var2); // L: 169
	}
}
